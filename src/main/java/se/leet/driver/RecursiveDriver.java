package se.leet.driver;

import se.leet.connection.Connection;
import se.leet.connection.PageConnection;
import se.leet.model.Result;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class RecursiveDriver implements Driver {

    Set<String> searchedPages = new HashSet<>();

    @Override
    public void search(String url, Consumer<Result> callback) {
        PageConnection firstConnection = new PageConnection(url);
        search(firstConnection, callback);
    }

    private void search(Connection connection, Consumer<Result> callback) {
        String url = connection.getUrl();
        if (searchedPages.contains(url)) {
            // Page has already been collected.
            return;
        }
        // Get the page.
        Connection.Data data = connection.connect();
        searchedPages.add(url);
        // Trigger callback.
        callback.accept(new Result(url, data.getData()));
        // Get all linked pages.
        data.getLinks().forEach(link -> search(link, callback));
    }
}
