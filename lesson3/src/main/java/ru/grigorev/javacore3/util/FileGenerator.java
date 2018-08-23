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
        int symbols = 1800 * pages;
        int content = 1;
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < symbols + 1; i++) {
            sb.append(content);
            if (i % 1800 == 0) {
                content++;
                writer.write(sb.toString());
                writer.flush();
                sb = new StringBuilder();
            }
        }
        writer.close();
        return file;

    }
}
