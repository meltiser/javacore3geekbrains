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

        Map<Method, Integer> methodsWithPrioritiesMap = new HashMap<>();

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
                methodsWithPrioritiesMap.put(method, method.getAnnotation(Test.class).priority());
            }
        }

        if (beforeSuiteCounter > 1 || afterSuiteCounter > 1) throw new RuntimeException();
        if (beforeSuiteCounter == 1) beforeSuiteMethod.invoke(instance);
        if (!methodsWithPrioritiesMap.isEmpty()) {
            Map<Method, Integer> sortedMap = sortByValue(methodsWithPrioritiesMap);
            for (Method testMethod : sortedMap.keySet()) {
                testMethod.invoke(instance);
            }
        }
        if (afterSuiteCounter == 1) afterSuiteMethod.invoke(instance);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
