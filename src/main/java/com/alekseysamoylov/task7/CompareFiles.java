package com.alekseysamoylov.task7;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Created by Aleksey Samoylov on 29.12.2015.
 */
public class CompareFiles {
    public static void main(String[] args) throws IOException {

        File file1 = new File("test1.txt");
        File file2 = new File("test2.txt");
        File file3 = new File("test3.txt");

        boolean compare1and2 = FileUtils.contentEquals(file1, file2);
        boolean compare2and3 = FileUtils.contentEquals(file2, file3);
        boolean compare1and3 = FileUtils.contentEquals(file1, file3);

        System.out.println("Are test1.txt and test2.txt the same? " + compare1and2);
        System.out.println("Are test2.txt and test3.txt the same? " + compare2and3);
        System.out.println("Are test1.txt and test3.txt the same? " + compare1and3);

    }
}
