package ru.grigorev.javacore3.methods;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Dmitriy Grigorev
 */
public class GenericUtils<T> {
    public void changeElementsInArray(T[] array, int index1, int index2) {
        if (index1 >= array.length) return;
        if (index2 >= array.length) return;
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
