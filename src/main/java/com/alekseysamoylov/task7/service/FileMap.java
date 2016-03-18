package com.alekseysamoylov.task7.service;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alekseysamoylov on 3/16/16.
 * Class works with files and id's list
 */
public class FileMap {

    /**
     * The main list!!! it saves every time, while program works.
     */
    public static ConcurrentHashMap<UUID, ArrayList<String>> fileMap = new ConcurrentHashMap<>();

    public static String realPath="";
    public static String CONF_FILE_PATH = realPath + "ROOT/filemap.ser";
    public static String FILE_PATH = realPath + "ROOT/";


    private FileMap() {
    }

    /**
     * Get list all saved files in ROOT directory
     *
     * @return all file's paths
     */
    public static synchronized ArrayList<UUID> getPathList() {
        ArrayList<UUID> pathList = new ArrayList<>();
        for (ConcurrentHashMap.Entry<UUID, ArrayList<String>> entry : fileMap.entrySet()) {
            pathList.add(entry.getKey());
        }
        return pathList;
    }

    /**
     * !!!!!!!!!!!!!!!!!!
     *
     * @param path
     * @return
     */
    public static synchronized UUID getIdWhilePath(String path) {
        return null;
    }


    /**
     * I don't use it. But maybe it will be helpful in future
     *
     * @param names array list if UUID doesn't exist
     * @param id
     */
    public static synchronized void addFileWithArray(ArrayList<String> names, UUID id) {
        fileMap.put(id, names);
    }

    /**
     * Save new file and generate id automatically
     *
     * @param name
     */
    public static void addNewFileWithoutId(String name) {
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        fileMap.put(CreateUUID.getUUID(), list);
    }

    /**
     * Save file name with ID if name is not contain already
     *
     * @param id
     * @param name
     */
    public static synchronized void addFileWithId(UUID id, String name) {
        int check = 0;
        for (int i = 0; i < fileMap.get(id).size(); i++) {
            if (fileMap.get(id).get(i).equals(name)) {
                check++;
                System.out.println("Contains equals file and alias");
            }
        }
        if (check == 0) {
            fileMap.get(id).add(name);
            System.out.println("Save new alias complete");
        } else {
            System.out.println("did not save new alias because it contains");
        }

    }

    /**
     * It returns path to our file
     *
     * @param id file's UUID
     * @return file's path
     */
    public static synchronized String getFileName(UUID id) {
        return fileMap.get(id).get(0);
    }

    /**
     * method searching files while name.
     * get all files and path if in name contains
     *
     * @param name symbols for search same files
     * @return hash map with key=name, value=id.
     */
    public static LinkedHashMap<String, UUID> search25Files(String name) {
        LinkedHashMap<String, UUID> findFiles = new LinkedHashMap<>();
        int counter = 1;
        System.out.println("Search " + name);
        System.out.println("filemap: " + fileMap);

        try {
            for (ConcurrentHashMap.Entry<UUID, ArrayList<String>> entry : fileMap.entrySet()) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if (entry.getValue().get(i).contains(name) && counter <= 25) {
                        findFiles.put(counter + ". " + entry.getValue().get(i), entry.getKey());
                        counter++;
                    }
                }
            }
            System.out.println("LIst" + findFiles);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return findFiles;
    }


    //**************************************SAVING FILE LIST***********************************

    /**
     * Check 'resource' directory and file. if doesn't exist -> create empty!!!
     * Can remove all your data!!!
     */
    public static synchronized void prepareDirectory() {
        File file = new File(FILE_PATH);
        file.mkdir();
        File fileConf = new File(CONF_FILE_PATH);

        try (FileWriter writer = new FileWriter(fileConf)) {
            System.out.println("ok in prepare");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save list of files exist in our Server in ROOT folder
     */
    public static synchronized void saveMap() {
        try (FileOutputStream outputStream = new FileOutputStream(CONF_FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(fileMap);
            System.out.println("I am in SAVING");
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load resource file with list of files and id.
     */
    public static synchronized void loadMap() {
        try (FileInputStream fileInputStream = new FileInputStream(CONF_FILE_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            fileMap = (ConcurrentHashMap<UUID, ArrayList<String>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * (КАК УКАЗАНО В ЗАДАНИИ! удаляются сразу все ссылки и файл, но я бы сделал, чтобы файл не удалялся, пока есть хоть одна ссылка)
     * Remove file from system and all alias from file list(Like in your plan)
     *
     * @param id
     */
    public static synchronized void deleteId(UUID id) {
        fileMap.remove(id);
        System.out.println("Aliases has deleted");
    }

}
