package concentration;

import java.util.Scanner;
import java.util.Random;

public class Util {
    private static Scanner scanner = new Scanner(System.in);

    public static void print(String str) { System.out.print(str + ": "); }
    public static void println(String str) {
        System.out.println(str);
    }

    public static String getString(String prompt, boolean sameLine) {
        String input = "";
        while (input.isEmpty()) {
            if (sameLine) {
                print(prompt);
            } else {
                println(prompt);
            }

            input = scanner.nextLine();
        }

        return input;
    }

    public static String getString(String prompt) {
        return getString(prompt, false);
    }

    public static int getInt(String prompt, boolean sameLine) {
        String input;
        int value = 0;
        boolean isValid = false;
        while (!isValid) {
            if (sameLine) {
                print(prompt);
            } else {
                println(prompt);
            }

            input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException e) {
                println("Invalid entry. Value must be an integer.");
            }
        }

        return value;
    }

    public static int getInt(String prompt) {
        return getInt(prompt, false);
    }

    public static int getPositiveInt(String prompt, boolean sameLine) {
        String input;
        int value = 0;
        boolean isValid = false;
        while (!isValid) {
            if (sameLine) {
                print(prompt);
            } else {
                println(prompt);
            }

            input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value > 0) {
                    isValid = true;
                } else {
                    println("Value must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                println("Invalid entry. Value must be a positive integer.");
            }
        }

        return value;
    }

    public static boolean getYorN(String prompt, boolean sameLine) {
        String input = "";
        boolean isValid = false;
        while (!isValid) {
            input = getString(prompt);
            isValid = input.toLowerCase().charAt(0) == 'y' || input.toLowerCase().charAt(0) == 'n';
            if (!isValid) {
                Util.println("Invalid entry.");
            }
        }

        return input.toLowerCase().charAt(0) == 'y';
    }

    public static boolean getYorN(String prompt) {
        return getYorN(prompt, false);
    }

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int getRandomInt(int max) {
        return getRandomInt(0, max);
    }

    public static String generateMenu(String title, String[] options) {
        String menu = title + '\n';
        String border = "----";
        for (int i = 0; i < title.length() && i < 80; i++) {
            border += '-';
        }
        menu += border + '\n';

        for (String option: options) {
            menu += option + '\n';
        }
        menu += "0. Quit\n";
        return menu;
    }
}
