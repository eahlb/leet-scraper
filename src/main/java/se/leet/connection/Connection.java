package se.leet.connection;

import java.util.stream.Stream;

public interface Connection {
    String getUrl();

    Data connect();

    interface Data {
        byte[] getData();

        Stream<Connection> getLinks();
    }
}
