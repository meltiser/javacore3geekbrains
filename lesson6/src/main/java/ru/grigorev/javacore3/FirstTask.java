package ru.grigorev.javacore3;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Dmitriy Grigorev
 */
public class FirstTask {
    public int[] getElementsAfterLastFour(int[] array) {
        if (array == null) return null;

        boolean containsFour = IntStream.of(array).anyMatch(x -> x == 4);
        if (!containsFour) throw new RuntimeException();

        int[] newArray = new int[0];
        if ((array.length - 1) == 4) return newArray;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                newArray = Arrays.copyOfRange(array, i + 1, array.length);
                break;
            }
        }
        return newArray;
    }
}
