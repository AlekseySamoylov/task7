package com.alekseysamoylov.task7.servlets;

import com.alekseysamoylov.task7.service.FileManipulation;
import com.alekseysamoylov.task7.service.FileMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by alekseysamoylov on 3/17/16.
 * Simple servlet...
 */
public class DeleteFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileMap.realPath = getServletContext().getRealPath("/") + "/";
        FileMap.loadMap();
        String id = req.getParameter("id");
        FileManipulation.deleteFile(id);
        FileMap.saveMap();
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>Task 7</title>" + "<body><h1>Delete complete</h1> <br> <a href='../task7/'>Go back</a></body></html>");
    }
}
