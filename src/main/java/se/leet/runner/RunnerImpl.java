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
        var driver = DriverFactory.build(driverType);
        driver.search(startPage, page -> FileUtil.createFile(page.getUrl(), page.getData()));
    }
}
