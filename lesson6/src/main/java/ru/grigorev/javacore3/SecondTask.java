package ru.grigorev.javacore3;

/**
 * @author Dmitriy Grigorev
 */
public class SecondTask {
    public static boolean containsFourOrOne(int[] array) {
        if (array == null || array.length == 0) return false;
        for (int number : array) {
            if (number != 1 && number != 4) {
                return false;
            }
        }
        return true;
    }
}
