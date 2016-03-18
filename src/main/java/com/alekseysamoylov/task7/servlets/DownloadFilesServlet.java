package com.alekseysamoylov.task7.servlets;

import com.alekseysamoylov.task7.service.FileManipulation;
import com.alekseysamoylov.task7.service.FileMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by alekseysamoylov on 3/17/16.
 * Downloading files from ID
 */
public class DownloadFilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        FileMap.realPath = getServletContext().getRealPath("/") + "/";
        String filePath = FileMap.FILE_PATH + req.getParameter("id");
        String id = req.getParameter("id");
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);

        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", FileMap.getFileName(UUID.fromString(id)));
        resp.setHeader(headerKey, headerValue);

        OutputStream outStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}

