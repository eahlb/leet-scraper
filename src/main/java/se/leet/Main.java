package se.leet;

import se.leet.driver.DriverType;
import se.leet.runner.Runner;

public class Main {
    public static void main(String[] args) {
        Runner.get(DriverType.SIMPLE)
                .run("http://books.toscrape.com/index.html");
    }
}