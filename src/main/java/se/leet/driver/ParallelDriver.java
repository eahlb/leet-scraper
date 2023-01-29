package se.leet.driver;

import se.leet.connection.Connection;
import se.leet.connection.PageConnection;
import se.leet.model.Result;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

class ParallelDriver implements Driver {

    Set<String> searchedPages = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void crawl(String url, Consumer<Result> callback) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            PageConnection firstConnection = new PageConnection(url);
            composePageCollectors(Stream.of(firstConnection), callback, executorService)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private CompletableFuture<Void> composePageCollectors(Stream<Connection> connections, Consumer<Result> callback, ExecutorService executorService) {
        // Collect data and links for each connection.
        CompletableFuture<?>[] linkedFutures = connections
                .map(connection -> CompletableFuture
                        // Collect page date.
                        .supplyAsync(new PageCollector(connection, callback), executorService)
                        // Compose page collectors on found links.
                        .thenComposeAsync(c -> composePageCollectors(c, callback, executorService), executorService)
                )
                .toArray(CompletableFuture[]::new);
        // Merge futures and return.
        return CompletableFuture.allOf(linkedFutures);

    }

    /**
     * Collects a page and supplies all linked objects by that page.
     * Implemented as a supplier to harmonize with CompletableFuture API.
     */
    private class PageCollector implements Supplier<Stream<Connection>> {

        private final Connection connection;
        private final Consumer<Result> callback;

        public PageCollector(Connection connection, Consumer<Result> callback) {
            this.connection = connection;
            this.callback = callback;
        }

        @Override
        public Stream<Connection> get() {
            String url = connection.getUrl();
            if (searchedPages.contains(url)) {
                // Page has already been collected.
                return Stream.of();
            }

            // Get the page.
            searchedPages.add(url);
            Connection.Data data = connection.connect();

            // Trigger callback.
            callback.accept(new Result(url, data.getData()));
            return data.getLinks();
        }
    }
}
