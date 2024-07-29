package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.Model.User;
import com.dbConnection.DbConnection;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginService")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		
		try {
			Connection con=DbConnection.getDbConnection();
			String query="SELECT * FROM users WHERE email=? AND pass=?";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet res= ps.executeQuery();
			if(res.next()) {
				User user=new User();
				user.setName(res.getString("name"));
				user.setEmail(res.getString("email"));
				user.setLang(res.getString("lang"));
				user.setGender(res.getString("gender"));
				
				HttpSession session=req.getSession();
				session.setAttribute("session_user", user);
				
				RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);
				
				
			}else {
				out.println("<center><h3>Something Went Wrong</h3></center>");
				RequestDispatcher rq=req.getRequestDispatcher("/login.html");
				rq.include(req,resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
