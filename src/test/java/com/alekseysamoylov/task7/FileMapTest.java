package com.alekseysamoylov.task7;

import com.alekseysamoylov.task7.service.CompareFiles;
import com.alekseysamoylov.task7.service.CreateUUID;
import com.alekseysamoylov.task7.service.FileManipulation;
import com.alekseysamoylov.task7.service.FileMap;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by alekseysamoylov on 3/16/16.
 */
public class FileMapTest {

    //Create save and load MainList file
    @Test
    public void test2(){

//        ArrayList<String> fileList = new ArrayList<>();
//        for(int i=1;i<5; i++) {
//            FileMap.addNewFileWithoutId("first.exe");
//            FileMap.addNewFileWithoutId("second.exe");
//        }
//
//        System.out.println(FileMap.search25Files("fir"));
//        FileMap.saveMap();
//        FileMap.fileMap = null;
//        System.out.println(FileMap.search25Files("fir"));
        FileMap.loadMap();
        System.out.println(FileMap.search25Files("q"));

    }

    @Test
    public void test3(){
        FileMap.loadMap();
        File file5 = new File("qqqqq.png");
        System.out.println(FileMap.getPathList());
        FileManipulation.addFile(file5);
        System.out.println(FileMap.getPathList());
        FileMap.saveMap();


    }

    @Test
    public void test4(){
        FileMap.loadMap();
        FileManipulation.loadFile("b5de33fa-2e0b-4ccd-a86e-a16f9b71091f");

    }
}
