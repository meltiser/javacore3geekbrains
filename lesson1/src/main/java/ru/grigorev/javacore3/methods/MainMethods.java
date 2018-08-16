package ru.grigorev.javacore3.methods;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
public class MainMethods {
    private static String[] testStringArray = {"London", "is", "the", "capital"};
    private static Integer[] testIntArray = {2, 4, 6, 8};

    public static void main(String[] args) {
        showFirstTask();
        showSecondTask();
    }

    public static void showFirstTask() {
        new GenericUtils<String>().changeElementsInArray(testStringArray, 0, 3);
        System.out.println(Arrays.toString(testStringArray)); // [capital, is, the, London]

        new GenericUtils<Integer>().changeElementsInArray(testIntArray, 1, 2);
        System.out.println(Arrays.toString(testIntArray)); // [2, 6, 4, 8]
    }

    public static void showSecondTask() {
        List<String> stringArrayList = new GenericUtils<String>().arrayToArrayList(testStringArray);
        stringArrayList.add("of");
        System.out.println(stringArrayList); // [capital, is, the, London, of]

        List<Integer> intArrayList = new GenericUtils<Integer>().arrayToArrayList(testIntArray);
        intArrayList.remove(0);
        System.out.println(intArrayList); // [6, 4, 8]
    }
}
