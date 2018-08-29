package ru.grigorev.javacore3.thirdTask;

/**
 * @author Dmitriy Grigorev
 */
public class ScannerDevice implements Runnable {
    private static ScannerDevice instance = null;

    private ScannerDevice() {
    }

    public static ScannerDevice getInstance() {
        if (instance == null) {
            synchronized (ScannerDevice.class) {
                if (instance == null) {
                    instance = new ScannerDevice();
                    return instance;
                }
            }
        } else {
            System.out.println("Wait until scanner finishes scanning");
        }
        return null;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i += 20) {
            String percent = "" + i;
            System.out.println("Scanning... " + percent + "% completed");
            try {
                Thread.sleep(50); //better make longer delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Scanner finished");
        instance = null;
    }
}
