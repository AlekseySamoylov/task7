package com.alekseysamoylov.task7.servlets;

import com.alekseysamoylov.task7.service.FileMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by alekseysamoylov on 3/17/16.
 * Show 25 files from search text field
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileMap.realPath = getServletContext().getRealPath("/") + "/";

        FileMap.loadMap();
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        LinkedHashMap<String, UUID> fileMap25 = new LinkedHashMap<>();
        String html = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>Task 7</title>" + "<html><body><h1>Result list!!!</h1><br><table border='1'><tr><th>Name</th><th>UUID</>";
        fileMap25 = FileMap.search25Files(name);
        System.out.println("Search " + name);
        for (Map.Entry<String, UUID> entry : fileMap25.entrySet()) {
            html += "<tr>";
            html += "<td>" + entry.getKey() + "</td><td>" + entry.getValue().toString() + "</td>";
            html += "<tr>";

        }
        html += "</table><p>Copy UUID if you want to download or delete file</p><br><a href='../task7/'>Go back to main</a></body></html>";
        writer.write(html);
        html = null;
    }
}
