package se.leet.driver;

public class DriverFactory {

    public static Driver build(DriverType type) {
        return switch (type) {
            case PARALLEL -> new ParallelDriver();
            case RECURSIVE -> new RecursiveDriver();
            case SIMPLE -> new SimpleDriver();
        };
    }
}
