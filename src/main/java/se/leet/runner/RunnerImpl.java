package se.leet.runner;

import se.leet.data.Page;
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
        driver.search(startPage, this::pageFound);
    }

    private void pageFound(Page page) {
        // TODO: 2023-01-26 Save pages for real.
        System.out.println("SAVING PAGE: " + page.getUrl());
    }
}
