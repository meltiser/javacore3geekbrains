package ru.grigorev.javacore3.thirdTask;

/**
 * @author Dmitriy Grigorev
 */

public class PrinterDevice implements Runnable {
    private static PrinterDevice instance = null;
    private static int pages;

    private PrinterDevice() {
    }

    public static PrinterDevice getInstance(int pages) {
        if (instance == null) {
            PrinterDevice.pages = pages;
            synchronized (PrinterDevice.class) {
                if (instance == null) {
                    instance = new PrinterDevice();
                    return instance;
                }
            }
        } else {
            System.out.println("Wait until printer finishes printing");
        }
        return null;
    }

    @Override
    public void run() {
        for (int i = 1; i < pages + 1; i++) {
            System.out.println("Printed " + i + " pages");
            try {
                Thread.sleep(50); //better make longer delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Printer finished");
        instance = null;
    }
}
