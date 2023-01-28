package se.leet;

import org.junit.jupiter.api.Test;
import se.leet.driver.DriverType;
import se.leet.output.OutputHandler;
import se.leet.runner.Runner;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

class BenchmarkTest {

    @Test
    void driverBenchmarks() {
        OutputHandler.disable();
        HashMap<DriverType, Long> result = new HashMap<>();
        Arrays.stream(DriverType.values())
                .forEach(type -> {
                    System.out.printf("Starting run for %s%n", type);
                    Runner runner = Runner.get(type);
                    Instant start = Instant.now();
                    runner.run("http://books.toscrape.com/index.html");
                    Instant finish = Instant.now();
                    long seconds = Duration.between(start, finish).getSeconds();
                    result.put(type, seconds);
                    System.out.printf("Finished run for %s in %s seconds%n", type, seconds);
                });
        // {PARALLEL=50, SIMPLE=410, RECURSIVE=409}
        System.out.println(result);
    }
}