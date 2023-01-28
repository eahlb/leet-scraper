package se.leet.driver;

import se.leet.connection.Connection;
import se.leet.connection.PageConnection;
import se.leet.model.Result;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

class SimpleDriver implements Driver {

    Set<Connection> foundPages = new HashSet<>();
    Set<String> collectedPages = new HashSet<>();

    @Override
    public void search(String startPage, Consumer<Result> callback) {
        // Add start page to initial set.
        PageConnection firstConnection = new PageConnection(startPage);
        foundPages.add(firstConnection);
        var nextPage = getNextConnection();

        while (nextPage.isPresent()) {
            // Fetch the next page.
            Connection connection = nextPage.get();
            Connection.Data data = connection.connect();
            String url = connection.getUrl();
            collectedPages.add(url);

            // Add any links to pages to be collected.
            data.getLinks().forEach(foundPages::add);

            // Trigger page found callback.
            callback.accept(new Result(url, data.getData()));

            // Get the next page to fetch.
            nextPage = getNextConnection();
        }
    }

    private Optional<Connection> getNextConnection() {
        return foundPages.stream()
                .filter(c -> !collectedPages.contains(c.getUrl()))
                .findFirst();
    }
}
