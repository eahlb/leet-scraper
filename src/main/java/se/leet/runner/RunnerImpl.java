package se.leet.runner;

import se.leet.driver.DriverFactory;
import se.leet.driver.DriverType;
import se.leet.util.FileUtil;

public class RunnerImpl implements Runner {

    private final DriverType driverType;

    RunnerImpl(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public void run(String startPage) {
        var driver = DriverFactory.build(driverType);
        driver.search(startPage, result -> FileUtil.createFile(result.fileName(), result.data()));
    }
}
