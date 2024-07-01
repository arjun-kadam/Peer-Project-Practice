package com.userManager;

import com.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        String firstName=req.getParameter("fname");
        String lastName=req.getParameter("lname");
        String username=req.getParameter("username");
        String email=req.getParameter("email");
        String password=req.getParameter("password");

        Users u1=new Users();
        u1.setFirstName(firstName);
        u1.setLastName(lastName);
        u1.setUserName(username);
        u1.setEmail(email);
        u1.setPassword(password);

        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session=factory.openSession();
        Transaction tx=session.beginTransaction();
        session.persist(u1);
        tx.commit();
        session.close();

        resp.sendRedirect("success.html");


    }
}
