package se.leet;

import se.leet.driver.DriverType;
import se.leet.runner.Runner;

public class Main {
    public static void main(String[] args) {
        // Get arguments, or set default values.
        var driverType = args.length > 0 ? DriverType.valueOf(args[0].toUpperCase()) : DriverType.PARALLEL;
        var url = args.length > 1 ? args[1] : "http://books.toscrape.com/index.html";
        // Start scraping process.
        Runner.get(driverType).run(url);
    }
}