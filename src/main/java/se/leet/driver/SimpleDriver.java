package se.leet.driver;

import se.leet.data.Page;
import se.leet.data.Result;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

class SimpleDriver implements Driver {

    Set<String> foundPages = new HashSet<>();
    Set<String> collectedPages = new HashSet<>();

    @Override
    public void search(String startPage, Consumer<Result> pageFound) {
        // Add start page to initial set.
        foundPages.add(startPage);
        var nextPage = getNextPage();

        while (nextPage.isPresent()) {
            // Fetch the next page.
            Page page = Page.build(nextPage.get());
            System.out.println("FOUND PAGE: " + page.getUrl());
            collectedPages.add(page.getUrl());

            // Add any links to pages to be collected.
            foundPages.addAll(page.getPageLinks());

            // TODO: 2023-01-26 Get all non-page resources.

            // Trigger page found callback.
            pageFound.accept(new Result(page.getUrl(), page.getData().getBytes(StandardCharsets.UTF_8)));

            // Get the next page to fetch.
            nextPage = getNextPage();
        }
    }

    private Optional<String> getNextPage() {
        return foundPages.stream()
                .filter(s -> !collectedPages.contains(s))
                .findFirst();
    }
}
