package ru.grigorev.javacore3;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Dmitriy Grigorev
 */
public class Car implements Runnable {
    private static final List<String> positions = new CopyOnWriteArrayList<>();
    private static int CARS_COUNT;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(MainClass.CARS_COUNT);

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public static List<String> getPositions() {
        return positions;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.prepareCDL.countDown();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        System.out.println(this.name + " закончил гонку!");
        MainClass.finishCDL.countDown();
        positions.add(this.name);
    }
}

