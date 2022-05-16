package com.example.zhyzhyian;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "searchServlet", value = "/search")
public class SearchServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        Search search = new Search();
        List<String> result = search.searchWord(request.getParameter("word"));

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>" +
                "<html>" +
                "    <head>" +
                "        <title>Search</title>" +
                "        <meta charset=\"UTF-8\">" +
                "<style> \n" +
                "                table, th, td { \n" +
                "                  border: 1px solid black; \n" +
                "                  border-collapse: collapse; \n" +
                "                  width: 100%; " +
                "                  text-align: center;" +
                "                } \n" +
                "                </style>" +
                "    </head>" +
                "    <body>" +
                "        <h1 style=\"text-align: center\">" +
                "            Zhyzhyian Iryna 502, variant 3" +
                "        </h1>" +
                "        <br/>" +
                "        <form method=\"post\" action=\"search\" style=\"text-align: center\">" +
                "            <input type=\"text\" name=\"word\">" +
                "            <input type=\"submit\" value=\"Search\">" +
                "        </form>");

        out.println("<br>");

        out.println("<table><tr><td><b>Word was found " + result.size() + " times</b></td></tr>");
        result.forEach(sentence -> {
            out.println("<tr><td>" + sentence + "</td></tr>");
        });
        out.println("</table>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}