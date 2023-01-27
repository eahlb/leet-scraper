package se.leet.data;

import org.jsoup.nodes.Document;
import se.leet.util.JsoupUtil;

import java.util.Collection;

public class Page {

    private final Document page;
    private final String url;

    private Page(Document page, String url) {
        this.page = page;
        this.url = url;
    }

    public static Page build(String url) {
        Document page = JsoupUtil.getPage(url);
        return new Page(page, url);
    }

    public String getData() {
        return page.toString();
    }

    public String getUrl() {
        return url;
    }

    public Collection<String> getPageLinks() {
        return JsoupUtil.getLinks(page);
    }

    public Collection<String> getResourceLinks() {
        return JsoupUtil.getResources(page);
    }
}
