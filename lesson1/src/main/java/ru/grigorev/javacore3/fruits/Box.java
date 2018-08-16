package ru.grigorev.javacore3.fruits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
public class Box<T extends Fruit> {
    private final String name;
    private List<T> boxList = new ArrayList<>();

    public Box(String name) {
        this.name = name;
    }

    public double getTotalWeight() {
        double totalWeight = 0.0;
        for (T fruit : boxList) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public int getQuantityOfFruits() {
        return boxList.size();
    }

    public void addFruit(T fruit) {
        boxList.add(fruit);
    }

    public void removeFruit() {
        if (boxList.size() > 0) boxList.remove(0);
        else throw new RuntimeException("Box is empty!");
    }

    public void removeFruit(int quantity){
        if (boxList.size() == quantity) boxList.clear();
        else if (boxList.size() > quantity) {
            for (int i = 0; i < quantity; i++) {
                boxList.remove(0);
            }
        } else throw new RuntimeException("Box has less elements than was passed in the method!");
    }

    public boolean compare(Box<? extends Fruit> box) {
        return this.getTotalWeight() == box.getTotalWeight();
    }

    public void copyAndDeleteFruits(Box<T> box) {
        box.setBoxList(new ArrayList<>(this.getBoxList()));
        this.clear();
    }

    public void clear() {
        boxList.clear();
    }

    public boolean isEmpty() {
        return boxList.isEmpty();
    }

    public void printInfo() {
        if (this.isEmpty()) System.out.println(this.getName() + " is empty!");
        else System.out.println(
                this.getName()
                        + " has "
                        + boxList.get(0).getDescription()
                        + "s in quantity of "
                        + this.getQuantityOfFruits()
                        + " and it's total weight: "
                        + this.getTotalWeight()
        );
    }

    public List<T> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<T> boxList) {
        this.boxList = boxList;
    }

    public String getName() {
        return name;
    }
}
