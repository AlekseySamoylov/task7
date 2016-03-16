package com.alekseysamoylov.task7.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Lets add files!!!
 * Created by alekseysamoylov on 3/16/16.
 */
public class FileManipulation {
    /**
     * Saving file. If equals file exist in server -> will save alias only from name to old 'UUID'
     * If file is not existing we create new file with name 'UUID' in server and make alias from name
     * @param file
     * @return
     */
    public static synchronized String addFile(File file){
        UUID id = new CompareFiles().checkFileExists(file, FileMap.getPathList());
        if(id!=null){
            FileMap.addFileWithId(id, file.toString());
        } else {
            id = CreateUUID.getUUID();
            ArrayList<String> firstArray = new ArrayList<>();
            firstArray.add(file.toString());
            try (FileOutputStream fileOutputStream = new FileOutputStream(id.toString());
                 FileInputStream fileInputStream = new FileInputStream(file)){
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer, 0, buffer.length);
                fileOutputStream.write(buffer, 0, buffer.length);
                FileMap.addFileWithArray(firstArray, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id.toString();
    }

    /**
     * Saving file, before search from id
     * @param id UUID
     */
    public static synchronized void loadFile(String id){
        String name = FileMap.getFileName(UUID.fromString(id));
        try (FileOutputStream fileOutputStream = new FileOutputStream(name);
             FileInputStream fileInputStream = new FileInputStream(id)){
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer, 0, buffer.length);
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized void deleteFile(String id){
        try {
            Path path = Paths.get(id);
            Files.delete(path);
            FileMap.deleteId(UUID.fromString(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
