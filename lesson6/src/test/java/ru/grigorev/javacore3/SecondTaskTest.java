package ru.grigorev.javacore3;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dmitriy Grigorev
 */
public class SecondTaskTest {

    @Test
    public void test1() {
        int[] inputArray = {1, 1, 1, 1};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertTrue(result);
    }

    @Test
    public void test2() {
        int[] inputArray = {4, 4, 4, 4};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertTrue(result);
    }

    @Test
    public void test3() {
        int[] inputArray = {1, 4, 4, 1, 1, 4, 1};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertTrue(result);
    }

    @Test
    public void test4() {
        int[] inputArray = {2, 2, 3, 7};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertFalse(result);
    }

    @Test
    public void test5() {
        int[] inputArray = {1, 2, 3, 4};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertFalse(result);
    }

    @Test
    public void testEmpty() {
        int[] inputArray = {};
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertFalse(result);
    }

    @Test
    public void testNull() {
        int[] inputArray = null;
        boolean result = SecondTask.containsFourOrOne(inputArray);
        Assert.assertFalse(result);
    }
}
