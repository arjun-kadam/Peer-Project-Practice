package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



import com.dbConnection.DbConnection;

@WebServlet("/registerService")
public class Register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String lang=req.getParameter("lang");
		String gender=req.getParameter("sex");
		String pass=req.getParameter("pass");
		
		try {
		Connection con=	DbConnection.getDbConnection();
		
		String query="INSERT INTO users VALUES(?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, lang);
		ps.setString(4, gender);
		ps.setString(5, pass);
		
		int count =ps.executeUpdate();
		if (count>0) {
			out.println("<center><h3>Registration Success</h3></center>");
			RequestDispatcher rq=req.getRequestDispatcher("/login.html");
			rq.include(req,resp);
		} else {
			out.println("<center><h3>Something Went Wrong</h3></center>");
			RequestDispatcher rq=req.getRequestDispatcher("/register.html");
			rq.include(req,resp);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
