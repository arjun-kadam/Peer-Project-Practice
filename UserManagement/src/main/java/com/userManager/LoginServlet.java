package com.userManager;

import com.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session=factory.openSession();

        String userName=req.getParameter("username");
        String password=req.getParameter("password");

        Users u1=(Users)session.get(Users.class,userName);
        String dbUserName=u1.getUserName();
        String dbPassword=u1.getPassword();
        String fName=u1.getFirstName();

        if (Objects.equals(dbUserName, userName) && Objects.equals(dbPassword, password)){
            out.println("<h1>Login Success</h1> <br>");
            out.println("<h2>Welcome Back "+fName+"</h2>");
        }
        else {
            out.println("<h1>Login Failed</h1> <br>");
        }
        session.close();
    }
}
