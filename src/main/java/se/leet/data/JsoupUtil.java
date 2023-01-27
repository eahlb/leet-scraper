package se.leet.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class JsoupUtil {

    static Document getPage(String url) {
        Connection connection = Jsoup.connect(url);
        try {
            return connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Set<String> getLinks(Document page) {
        return new HashSet<>(page.getElementsByTag("a").eachAttr("abs:href"));
    }

    static Set<String> getResources(Document page) {
        // TODO: 2023-01-27 Add style sheets
        return new HashSet<>(page.getElementsByTag("img").eachAttr("abs:src"));
    }
}
