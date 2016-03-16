package com.alekseysamoylov.task7.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

/**
 * Created by Aleksey Samoylov on 29.12.2015.
 * Compare files
 */
public class CompareFiles {

    /**
     * Check file's existing while content.
     * @param fileForCheck file for checking
     * @param pathList list files paths from FileMap class
     * @return true if exist or false if doesn't exist.
     */
    public UUID checkFileExists(File fileForCheck, ArrayList<UUID> pathList){
        UUID id = null;
        for(int i=0;i<pathList.size();i++){
            File existFile = new File(pathList.get(i).toString());
            try {
                if(FileUtils.contentEquals(fileForCheck, existFile)){
                    id = pathList.get(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;

    }

}
