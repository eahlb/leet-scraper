package se.leet.driver;

import se.leet.data.Page;
import se.leet.data.Result;
import se.leet.util.JsoupUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RecursiveDriver implements Driver {

    Set<String> searchedPages = new HashSet<>();
    Set<String> collectedResources = new HashSet<>();

    @Override
    public void search(String url, Consumer<Result> pageFound) {
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
        pageFound.accept(new Result(page.getUrl(), page.getData().getBytes(StandardCharsets.UTF_8)));
        // Get all linked pages.
        page.getPageLinks().forEach(link -> search(link, pageFound));

        // Get all un-collected linked resources.
        page.getResourceLinks().stream()
                .filter(Predicate.not(collectedResources::contains))
                .forEach(link -> {
                    collectedResources.add(link);
                    byte[] data = JsoupUtil.getData(link);
                    pageFound.accept(new Result(link, data));
                });
    }
}
