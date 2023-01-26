package se.leet.driver;

public class DriverFactory {

    public static Driver build(DriverType type) {
        return switch (type) {
            case SIMPLE -> new SimpleDriver();
        };
    }
}
