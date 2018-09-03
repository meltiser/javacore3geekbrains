package ru.grigorev.javacore3;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dmitriy Grigorev
 */
public class MainClass {
    public static final int CARS_COUNT = 6;
    public static final CountDownLatch prepareCDL = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch finishCDL = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        prepareCDL.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finishCDL.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("Позиции: ");
        for (int i = 0; i < Car.getPositions().size(); i++) {
            System.out.println((i + 1) + " место: " + Car.getPositions().get(i));
        }
    }
}
