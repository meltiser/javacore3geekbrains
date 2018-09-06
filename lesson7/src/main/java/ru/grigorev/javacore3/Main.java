package ru.grigorev.javacore3;

import ru.grigorev.javacore3.annotations.AfterSuite;
import ru.grigorev.javacore3.annotations.BeforeSuite;
import ru.grigorev.javacore3.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Dmitriy Grigorev
 */
public class Main {
    public static final Class TEST_CLASS = TestClass.class;

    public static void main(String[] args) {
        try {
            start(TEST_CLASS);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class clazz) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {
        Object instance = clazz.getConstructor().newInstance();
        Method[] methods = clazz.getMethods();

        int beforeSuiteCounter = 0;
        Method beforeSuiteMethod = null;

        int afterSuiteCounter = 0;
        Method afterSuiteMethod = null;

        //Set<Method> methodsWithPrioritiesSet = new TreeSet<>(Comparator.comparing(m -> m.getAnnotation(Test.class).priority()));
        List<Method> methodWithPrioritiesList = new ArrayList<>();

        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuiteCounter == 0) {
                    beforeSuiteMethod = method;
                }
                beforeSuiteCounter++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuiteCounter == 0) {
                    afterSuiteMethod = method;
                }
                afterSuiteCounter++;
            }
            if (method.getAnnotation(Test.class) != null) {
               // methodsWithPrioritiesSet.add(method);
                methodWithPrioritiesList.add(method);
            }
        }

        if (beforeSuiteCounter > 1 || afterSuiteCounter > 1) throw new RuntimeException();
        if (beforeSuiteCounter == 1) beforeSuiteMethod.invoke(instance);
        /*if (!methodsWithPrioritiesSet.isEmpty()) {
            for (Method method : methodsWithPrioritiesSet) {
                method.invoke(instance);
            }
        }*/
        if (!methodWithPrioritiesList.isEmpty()) {
            methodWithPrioritiesList.sort(Comparator.comparing(m -> m.getAnnotation(Test.class).priority()));
            for (Method method : methodWithPrioritiesList) {
                method.invoke(instance);
            }
        }
        if (afterSuiteCounter == 1) afterSuiteMethod.invoke(instance);
    }
}
