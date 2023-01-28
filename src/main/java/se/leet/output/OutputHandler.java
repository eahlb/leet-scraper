package se.leet.output;

public class OutputHandler {

    private static boolean enabled = true;

    public static void log(String operation, String object) {
        if (enabled) {
            System.out.printf("%-35s %-10s %s%n", Thread.currentThread().getName(), operation, object);
        }
    }

    public static void disable() {
        enabled = false;
    }
}
