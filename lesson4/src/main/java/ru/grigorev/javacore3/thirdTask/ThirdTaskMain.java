package ru.grigorev.javacore3.thirdTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Dmitriy Grigorev
 */
public class ThirdTaskMain {
    private static MFD mfd;

    public static void main(String[] args) {
        mfd = new MFDImpl();
        getCommandFromConsole();
    }

    public static void getCommandFromConsole() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type command: (/stop - finishes the program)");
            System.out.println("/print [pages] - prints the required number of pages");
            System.out.println("/scan - scanning the document");
            while (true) {
                String command = br.readLine();
                if (command.equalsIgnoreCase("/stop")) {
                    break;
                }
                if (command.startsWith("/print ")) {
                    String[] parts = command.split("\\s+");
                    if (parts.length < 2) continue;
                    int pages;
                    try {
                        pages = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    mfd.print(pages);
                }
                if (command.equalsIgnoreCase("/scan")) {
                    mfd.scan();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
