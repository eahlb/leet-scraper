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
    public void run(String domain, String startPage) {
        var driver = DriverFactory.build(driverType);
        driver.search(domain, startPage, this::pageFound);
    }

    private void pageFound(Page page) {
        System.out.println("FOUND page " + page.getPath());
        System.out.println("TODO: write file to " + page.getPath());
    }
}
