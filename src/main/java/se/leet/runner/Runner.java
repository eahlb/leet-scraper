package se.leet.runner;

import se.leet.driver.DriverType;

public interface Runner {
    void run(String domain, String startPage);

    static Runner get(DriverType driverType) {
        return new RunnerImpl(driverType);
    }
}
