<%@page import="com.Model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%
        User user=(User)session.getAttribute("session_user");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome <%= user.getName() %> !!! </title>
</head>
<body>
 
̥<center><h1>Welcome Back <%= user.getName() %> !!! </h1></center>
<center>
   <h3>Email : <%= user.getEmail() %> </h3>
   <h3>Selected Course : <%= user.getLang().toUpperCase() %> </h3>
   <h3>Gender: <%= user.getGender() %> </h3>
   <a href="logoutService"><button>Logout</button></a>
</center>

</body>
</html>