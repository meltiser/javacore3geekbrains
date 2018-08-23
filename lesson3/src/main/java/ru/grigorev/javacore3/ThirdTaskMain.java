package ru.grigorev.javacore3;

import ru.grigorev.javacore3.util.FileGenerator;

import java.io.*;

/**
 * @author Dmitriy Grigorev
 */
public class ThirdTaskMain {
    private static File file;

    public static void main(String[] args) {
        try {
            file = new FileGenerator().createFileWithPages("test.txt", 12);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int page;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type command: (/stop - finishes the program)");
            while (true) {
                String input = br.readLine();
                if (input.equalsIgnoreCase("/stop")) {
                    break;
                }
                try {
                    page = Integer.parseInt(input);
                    showPage(page);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showPage(int page) {
        int pageStart = page * 1800 - 1800;
        int pageFinish = pageStart + 1800;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            for (int i = pageStart; i < pageFinish; i++) {
                raf.seek(i);
                System.out.print((char) raf.read());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
