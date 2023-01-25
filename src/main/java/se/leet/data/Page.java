package se.leet.data;

import org.jsoup.nodes.Document;

import java.util.Collection;

public class Page {

    private final Document page;
    private final String path;

    private Page(Document page, String path) {
        this.page = page;
        this.path = path;
    }

    public static Page build(String domain, String path) {
        Document page = JsoupUtil.getPage(domain, path);
        return new Page(page, path);
    }

    public String getData() {
        return page.data();
    }

    public String getPath() {
        return path;
    }

    public Collection<String> getPageLinks() {
        return JsoupUtil.getLinks(page);
    }

    public Collection<String> getResourceLinks() {
        return JsoupUtil.getResources(page);
    }
}
