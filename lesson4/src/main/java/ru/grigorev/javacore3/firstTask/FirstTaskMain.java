package ru.grigorev.javacore3.firstTask;

/**
 * @author Dmitriy Grigorev
 */
public class FirstTaskMain {
    private static ThreadsLogic threadsLogic = new ThreadsLogic();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> threadsLogic.printA());
        Thread thread2 = new Thread(() -> threadsLogic.printB());
        Thread thread3 = new Thread(() -> threadsLogic.printC());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
