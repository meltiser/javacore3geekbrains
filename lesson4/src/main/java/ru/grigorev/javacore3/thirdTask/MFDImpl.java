package ru.grigorev.javacore3.thirdTask;

/**
 * @author Dmitriy Grigorev
 */
public class MFDImpl implements MFD {
    public void print(int pages) {
        Thread thread = new Thread(PrinterDevice.getInstance(pages));
        thread.start();
    }

    public void scan() {
        Thread thread = new Thread(ScannerDevice.getInstance());
        thread.start();
    }
}
