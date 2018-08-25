package ru.grigorev.javacore3.util;

import java.io.*;
import java.util.Random;

/**
 * @author Dmitriy Grigorev
 */
public class FileGenerator {

    public File createFile(String name) throws IOException {
        File file = new File(name);
        file.createNewFile();
        return file;
    }

    public File createFileWithRandomBytes(String name, int numBytes) throws IOException {
        File file = new File(name);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] byteArr = new byte[numBytes];
        Random random = new Random();
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) random.nextInt(255);
        }
        fos.write(byteArr);
        fos.close();
        return file;
    }

    public File createFileWithPages(String name, int pages) throws IOException {
        File file = new File(name);
        file.createNewFile();
        Integer content = 1;
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < pages; i++) {
            for (int j = 0; j < 1800; j++) {
                writer.write(content.toString());
            }

            if (content == 9) {
                content = 0;
            } else content++;

            writer.flush();
        }
        writer.close();
        return file;

    }
}
