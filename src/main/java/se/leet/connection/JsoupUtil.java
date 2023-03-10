package se.leet.connection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class JsoupUtil {

    public static Document getPage(String url) {
        Connection connection = Jsoup.connect(url);
        try {
            return connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getData(String url) {
        Connection connection = Jsoup.connect(url).ignoreContentType(true);
        try {
            return connection.execute().bodyAsBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<String> getLinks(Document page) {
        return new HashSet<>(page.getElementsByTag("a").eachAttr("abs:href"));
    }

    public static Set<String> getResources(Document page) {
        Set<String> result = new HashSet<>();
        result.addAll(page.getElementsByTag("img").eachAttr("abs:src"));
        result.addAll(page.getElementsByTag("link").eachAttr("abs:href"));
        return result;
    }
}
