package ru.grigorev.javacore3;

import ru.grigorev.javacore3.util.FileGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
public class SecondTaskMain {
    private static SequenceInputStream sis;
    private static FileOutputStream fos;

    public static void main(String[] args) {
        try {
            List<FileInputStream> fisList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                fisList.add(
                        new FileInputStream(
                                new FileGenerator().createFileWithRandomBytes(i + ".txt", 100)));
            }
            Enumeration<FileInputStream> fileInputStreamEnumeration = Collections.enumeration(fisList);

            sis = new SequenceInputStream(fileInputStreamEnumeration);
            File outputFile = new FileGenerator().createFile("output.txt");
            fos = new FileOutputStream(outputFile);

            int rb = sis.read();
            while (rb != -1) {
                fos.write(rb);
                rb = sis.read();
            }

            System.out.println(outputFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
