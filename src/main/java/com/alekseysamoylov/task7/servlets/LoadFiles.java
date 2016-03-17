package com.alekseysamoylov.task7.servlets;

import com.alekseysamoylov.task7.service.FileManipulation;
import com.alekseysamoylov.task7.service.FileMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by alekseysamoylov on 3/16/16.
 * Servlet class about downloading files
 */
public class LoadFiles extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(1024 * 1024);

        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 5);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                processUploadedFile(item);
                writer.write("<html><body><h1>Downloading complete!!!</h1><br><a href='../'>Come back to main</a></body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }


    }

    private synchronized void processUploadedFile(FileItem item) throws Exception {
        if (!new File(FileMap.CONF_FILE_PATH).exists()) {
            System.out.println("Create all directory and files(First start)");
            FileMap.prepareDirectory();
            FileMap.saveMap();
        }
        String tempName = item.getName();
        try {
            FileMap.loadMap();
            File newFile = new File(tempName);
            newFile.createNewFile();
            item.write(newFile);
            FileManipulation.addFile(newFile);
            System.out.println("File has saved");
            FileMap.saveMap();
        } finally {
            Path path = Paths.get(tempName);
            Files.delete(path);
            tempName = null;
            System.out.println("Temp file has deleted");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<html><body><h1>Hello!!!</h1></body></html>");
        writer.close();

    }
}
