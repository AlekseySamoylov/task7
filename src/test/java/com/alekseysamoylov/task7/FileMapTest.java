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
//        FileMap.prepareDirectory();
//        FileMap.saveMap();
        FileMap.loadMap();
//        FileMap.addNewFileWithoutId("asdfdasf");
//        FileMap.saveMap();
//        FileMap.fileMap = null;
//        FileMap.loadMap();
//        System.out.println(FileMap.search25Files("asd"));
//        System.out.println(FileMap.fileMap);


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

    @Test
    public void test5(){
        FileMap.loadMap();
        FileManipulation.deleteFile("b5de33fa-2e0b-4ccd-a86e-a16f9b71091f");
    }

    @Test
    public void test6(){
        System.out.println(!new File("ROOT/filemap/filemap.ser").exists());
        FileMap.prepareDirectory();
        System.out.println(!new File("ROOT/filemap/filemap.ser").exists());

    }
}
