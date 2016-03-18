package com.alekseysamoylov.task7;

import com.alekseysamoylov.task7.service.CompareFiles;
import com.alekseysamoylov.task7.service.CreateUUID;
import com.alekseysamoylov.task7.service.FileManipulation;
import com.alekseysamoylov.task7.service.FileMap;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by alekseysamoylov on 3/16/16.
 * I can write beautiful tests, but I had not a lot of time.
 * And I write only what help me to check service classes.
 *
 * I used my files in this tests and it will not work in your machine. Because I hide almost tests.
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
        FileMap.prepareDirectory();
        FileMap.saveMap();
        FileMap.loadMap();
//        FileMap.addNewFileWithoutId("asdfdasf");
//        FileMap.saveMap();
//        FileMap.fileMap = null;
//        FileMap.loadMap();
//        System.out.println(FileMap.search25Files("asd"));
//        System.out.println(FileMap.fileMap);


    }

//    @Test
//    public void test3(){
//        FileMap.loadMap();
//        File file5 = new File("qqqqq.png");
//        System.out.println(FileMap.getPathList());
//        FileManipulation.addFile(file5);
//        System.out.println(FileMap.getPathList());
//        FileMap.saveMap();
//        FileMap.loadMap();
//        FileManipulation.deleteFile("b5de33fa-2e0b-4ccd-a86e-a16f9b71091f");
//        FileMap.saveMap();
//    }

//    @Test
//    public void test4(){
//        FileMap.loadMap();
//        FileManipulation.loadFile("b5de33fa-2e0b-4ccd-a86e-a16f9b71091f");
//
//    }


    @Test
    public void test6(){
        System.out.println(!new File("ROOT/filemap/filemap.ser").exists());
        FileMap.prepareDirectory();
        System.out.println(!new File("ROOT/filemap/filemap.ser").exists());

    }

    @Test
    public void test7(){
        ArrayList<UUID> fileList = new ArrayList<>();
        fileList.add(UUID.fromString("0c4b91f6-a2c2-48d7-a1f2-c817aa3ee762"));
        System.out.println(new CompareFiles().checkFileExists(new File("0c4b91f6-a2c2-48d7-a1f2-c817aa3ee762"), fileList));
    }
}
