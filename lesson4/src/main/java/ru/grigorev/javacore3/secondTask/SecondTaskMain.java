package ru.grigorev.javacore3.secondTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Dmitriy Grigorev
 */
public class SecondTaskMain {
    private static File file = new File("test.txt");
    private static BufferedWriter out;

    static {
        try {
            file.createNewFile();
            out = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (true) {
                write10StringsToFile();
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                write10StringsToFile();
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {
                write10StringsToFile();
            }
        });

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(500);
    }

    private static synchronized void write10StringsToFile() {
        for (int i = 0; i < 10; i++) {
            try {
                out.write(Thread.currentThread().getName() + " " + i + " \r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
