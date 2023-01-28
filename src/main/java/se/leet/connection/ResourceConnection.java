package se.leet.connection;

import java.util.stream.Stream;

public record ResourceConnection(String url) implements Connection {
    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Data connect() {
        byte[] data = JsoupUtil.getData(url);
        return new Data() {
            @Override
            public byte[] getData() {
                return data;
            }

            @Override
            public Stream<Connection> getLinks() {
                return Stream.of();
            }
        };
    }
}
