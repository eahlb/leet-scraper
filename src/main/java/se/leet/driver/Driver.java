package se.leet.driver;

import se.leet.data.Page;

import java.util.function.Consumer;

public interface Driver {
    void search(String domain, String path, Consumer<Page> pageFound);
}
