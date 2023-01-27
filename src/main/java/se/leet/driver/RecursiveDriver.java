package se.leet.driver;

import se.leet.data.Page;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class RecursiveDriver implements Driver {

    Set<String> searchedPages = new HashSet<>();

    @Override
    public void search(String url, Consumer<Page> pageFound) {
        if (searchedPages.contains(url)) {
            // Page has already been collected.
            return;
        } else {
            // Add page to searched pages.
            searchedPages.add(url);
        }
        // Get the page.
        Page page = Page.build(url);
        // Trigger callback.
        pageFound.accept(page);
        // Get all linked pages.
        page.getPageLinks().forEach(link -> search(link, pageFound));
    }
}
