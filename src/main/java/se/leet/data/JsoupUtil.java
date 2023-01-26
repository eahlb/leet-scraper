package se.leet.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        // TODO: 2023-01-26 Handle all link tags.
        var result = new HashSet<>(page.getElementsByTag("a").eachAttr("href"));
        // TODO: 2023-01-26 Handle all references.
        return result.stream()
                .filter(s -> s.startsWith("catalogue"))
                .collect(Collectors.toSet());
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
