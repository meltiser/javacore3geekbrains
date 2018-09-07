package ru.grigorev.javacore3;

import ru.grigorev.javacore3.annotations.AfterSuite;
import ru.grigorev.javacore3.annotations.BeforeSuite;
import ru.grigorev.javacore3.annotations.Test;

/**
 * @author Dmitriy Grigorev
 */
public class TestClass {

    @BeforeSuite
    public void before() {
        System.out.println("Preparing tests...");
    }


    @Test(priority = 3)
    public void test1() {
        System.out.println("Test #1. Priority = 3");
    }

    @Test(priority = 1)
    public void test2() {
        System.out.println("Test #2. Priority = 1");
    }

    @Test(priority = 5)
    public void test3() {
        System.out.println("Test #3. Priority = 5");
    }

    @Test(priority = 9)
    public void test4() {
        System.out.println("Test #4. Priority = 9");
    }

    @Test(priority = 3)
    public void test5() {
        System.out.println("Test #5. Priority = 3");
    }

    @AfterSuite
    public void after() {
        System.out.println("Finishing tests...");
    }
}
