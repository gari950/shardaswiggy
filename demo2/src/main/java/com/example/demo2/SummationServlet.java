package com.example.demo2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SummationServlet", value = "/SummationServlet")

public class SummationServlet extends HttpServlet{

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        int t1=Integer.parseInt(request.getParameter("t1"));
        int t2=Integer.parseInt(request.getParameter("t2"));
        PrintWriter out = response.getWriter();
        int c= t1+t2;
        out.println("<html><body><h1>"+"Sum of two no are"+"</h1>"+c);
        out.println("</body></html>");
        out.flush();
    }
}
