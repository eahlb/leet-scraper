package se.leet.driver;

import se.leet.model.Result;

import java.util.function.Consumer;

public interface Driver {
    void crawl(String url, Consumer<Result> callback);
}
