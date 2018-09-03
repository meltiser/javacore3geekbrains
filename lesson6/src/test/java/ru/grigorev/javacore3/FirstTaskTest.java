package ru.grigorev.javacore3;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dmitriy Grigorev
 */

public class FirstTaskTest {
    private FirstTask firstTask;

    @Before
    public void init() {
        firstTask = new FirstTask();
    }

    @Test
    public void test1() {
        int[] inputArray = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] expectedArray = {1, 7};
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
        Assert.assertArrayEquals(expectedArray, resultArray);
    }

    @Test(expected = RuntimeException.class)
    public void test2() {
        int[] inputArray = {1, 2, 2, 3, 1, 7};
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
    }

    @Test
    public void test3() {
        int[] inputArray = {1, 2, 2, 3, 1, 7, 4};
        int[] expectedArray = {};
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
        Assert.assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void test4() {
        int[] inputArray = {4, 1, 2, 2, 3, 1, 7};
        int[] expectedArray = {1, 2, 2, 3, 1, 7};
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
        Assert.assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void testNull() {
        int[] inputArray = null;
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
        Assert.assertNull(resultArray);
    }

    @Test(expected = RuntimeException.class)
    public void testEmpty() {
        int[] inputArray = {};
        int[] resultArray = firstTask.getElementsAfterLastFour(inputArray);
    }
}
