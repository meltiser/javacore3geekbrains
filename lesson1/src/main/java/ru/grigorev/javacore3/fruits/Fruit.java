package ru.grigorev.javacore3.fruits;

/**
 * @author Dmitriy Grigorev
 */
public abstract class Fruit {
    private double weight;
    private String description = "";

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
