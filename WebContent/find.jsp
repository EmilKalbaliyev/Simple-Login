<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find user</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%	
if(request.getParameterValues("option")!=null){
		RequestDispatcher rd = request.getRequestDispatcher("search");
		rd.include(request, response); 
}
%>
<form action="find.jsp">
<p>Find a user:</p>
	<select name ="option">
		<option value="u_id">User id</option>
		<option value="username">User name</option>
		<option value="email">Email</option>
		<option value="firstname">First Name</option>
		<option value="lastname">Last Name</option>
	</select><br>
	<input type="text" name="text"> <span class="msg">${msg.msg}</span><br> 
	<input type="submit" value="Search">
</form>
	<br>
	<table style="width:50%">
	${th}
	<c:forEach var="person" items="${user}">
        <TR>
          <TD><c:out value="${person.u_id}"  /></TD>
          <TD><c:out value="${person.username}"  /></TD>
          <TD><c:out value="${person.password}"  /></TD>
          <TD><c:out value="${person.email}"  /></TD>
          <TD><c:out value="${person.firstName}"  /></TD>
          <TD><c:out value="${person.lastName}"  /></TD>          
        </TR>
      </c:forEach>
	</table>

</body>
</html>