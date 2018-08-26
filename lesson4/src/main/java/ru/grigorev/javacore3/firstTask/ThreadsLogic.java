package ru.grigorev.javacore3.firstTask;

/**
 * @author Dmitriy Grigorev
 */
public class ThreadsLogic {
    private static boolean isAPrinted = false;
    private static boolean isBPrinted = false;
    private static boolean isCPrinted = true;

    public synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            while (!isCPrinted) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            isAPrinted = true;
            isBPrinted = false;
            isCPrinted = false;
            notifyAll();
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < 5; i++) {
            while (!isAPrinted) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            isAPrinted = false;
            isBPrinted = true;
            isCPrinted = false;
            notifyAll();
        }
    }

    public synchronized void printC() {
        for (int i = 0; i < 5; i++) {
            while (!isBPrinted) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            isAPrinted = false;
            isBPrinted = false;
            isCPrinted = true;
            notifyAll();
        }
    }
}
