package se.leet.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class JsoupUtil {

    static Document getPage(String domain, String path) {
        String url = domain + path;
        Connection connection = Jsoup.connect(url);
        try {
            return connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Set<String> getLinks(Document page) {
        var result = new HashSet<String>();
        result.addAll(page.getElementsByTag("a").eachAttr("href"));
        result.addAll(page.getElementsByTag("link").eachAttr("href"));
        return result;
    }

    static Set<String> getResources(Document page) {
        return new HashSet<>(page.getElementsByTag("img").eachAttr("src"));
    }

    static Optional<String> getNextPage(Document page) {
        return page.getElementsByTag("a")
                .stream()
                .filter(Element::hasText)
                .filter(element -> element.text().equals("next"))
                .map(element -> element.attributes().get("href"))
                .findAny();
    }
}
