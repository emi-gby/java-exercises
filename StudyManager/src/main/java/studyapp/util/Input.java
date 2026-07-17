package studyapp.util;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Input(){}

    public static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer number.");
            }
        }
    }

    public static String readString(String msg) {
        String line;
        do {
            System.out.print(msg);
            line = SCANNER.nextLine().trim();
        } while (line.isEmpty());

        return line;
    }

}
