package ru.grigorev.javacore3.fruits;

/**
 * @author Dmitriy Grigorev
 */
public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>("Apple Box");
        for (int i = 0; i < 6; i++) { // adding 6 apples
            appleBox.addFruit(new Apple());
        }
        appleBox.printInfo(); //weight = 6.0

        Box<Orange> orangeBox = new Box<>("Orange Box");
        for (int i = 0; i < 4; i++) { //adding 4 oranges
            orangeBox.addFruit(new Orange());
        }
        orangeBox.printInfo(); //weight = 6.0
        System.out.println(appleBox.compare(orangeBox)); //true

        Box<Apple> appleBox2 = new Box<>("Another Apple Box"); //creating another apple box
        appleBox.copyAndDeleteFruits(appleBox2); //pour apples from first to second

        appleBox.printInfo(); // first box is empty
        appleBox2.printInfo(); // second box contains fruits from the first
    }
}
