package se.leet.driver;

import se.leet.data.Page;

import java.util.function.Consumer;

class SimpleDriver implements Driver {

    @Override
    public void search(String domain, String path, Consumer<Page> pageFound) {
        // TODO: 2023-01-26 Expand to continue search
        Page result = Page.build(domain, path);
        pageFound.accept(result);
    }
}
