package ru.grigorev.javacore3;

import ru.grigorev.javacore3.util.FileGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Dmitriy Grigorev
 */
public class FirstTaskMain {
    private static FileInputStream fis;

    public static void main(String[] args) {
        try {
            File file = new FileGenerator().createFileWithRandomBytes("test.txt", 50);
            fis = new FileInputStream(file);
            byte[] byteArray = new byte[fis.available()];
            fis.read(byteArray);
            for (byte b : byteArray) {
                System.out.print(b + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
