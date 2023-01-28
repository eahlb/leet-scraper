package se.leet.connection;

import org.jsoup.nodes.Document;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public record PageConnection(String getUrl) implements Connection {

    @Override
    public String getUrl() {
        return getUrl;
    }

    @Override
    public Data connect() {
        Document page = JsoupUtil.getPage(getUrl);
        return new Data() {
            @Override
            public byte[] getData() {
                return page.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Stream<Connection> getLinks() {
                return Stream.concat(
                        JsoupUtil.getLinks(page).stream().map(PageConnection::new),
                        JsoupUtil.getResources(page).stream().map(ResourceConnection::new)
                );
            }
        };
    }
}
