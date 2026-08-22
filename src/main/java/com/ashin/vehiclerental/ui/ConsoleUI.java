package com.ashin.vehiclerental.ui;

public class ConsoleUI {

    private static final String SEPARATOR = "========================================";
    private static final String DASH_SEPARATOR = "----------------------------------------";

    public static void printHeader(String title) {
        System.out.println(SEPARATOR);
        System.out.println("       " + title);
        System.out.println(SEPARATOR);
    }

    public static void printMenu(String... options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println();
    }

    public static void printSuccess(String message) {
        System.out.println("\n✓ " + message);
    }

    public static void printError(String message) {
        System.out.println("\n✗ Error: " + message);
    }

    public static void printInfo(String message) {
        System.out.println("\n" + message);
    }

    public static void printSeparator() {
        System.out.println(DASH_SEPARATOR);
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printGoodbye() {
        System.out.println(SEPARATOR);
        System.out.println("   Thank you for using Vehicle Rental System!");
        System.out.println(SEPARATOR);
    }
}
