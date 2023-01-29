package se.leet.runner;

import se.leet.driver.DriverFactory;
import se.leet.driver.DriverType;

public class RunnerImpl implements Runner {

    private final DriverType driverType;

    RunnerImpl(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public void run(String startPage) {
        // Get the driver.
        var driver = DriverFactory.build(driverType);
        // Start the crawl creating file on page found.
        driver.crawl(startPage, result -> FileUtil.createFile(result.fileName(), result.data()));
    }
}
