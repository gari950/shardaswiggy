package com.example.demo2;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();

        if(username.equals("garima")){
            if(password.equals("test@123")) {
                writer.println("<html><body><h1>" + "welcome " + username);
                writer.println("</h1></body></html>");
            }else{
                writer.println("<html><body><h1>");
                writer.println("Invalid username!");
                writer.println("</h1></body></html>");

           }

        }
        //writer.println("</body></html>");
        writer.flush();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        

    }

}
