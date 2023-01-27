package se.leet.driver;

import se.leet.data.Result;

import java.util.function.Consumer;

public interface Driver {
    void search(String url, Consumer<Result> pageFound);
}
