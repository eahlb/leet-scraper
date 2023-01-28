package se.leet.output;

public class OutputHandler {

    public static void log(String operation, String object) {
        System.out.printf("%-30s %-10s %s%n", Thread.currentThread().getName(), operation, object);
    }
}
