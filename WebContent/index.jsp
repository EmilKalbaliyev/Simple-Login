<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<body>
<form action="login" method="post">
<p>Welcome to our website! </p> <span>Have an account? </span> <br>
<input type="text" name= "username" placeholder="Username" value='${requestScope.uname}'required>  <span class="msg">${msg1.msg}</span><br>
<input type="password" name="pass" placeholder="Password" required>  <span class="msg">${msg2.msg}</span><br>
<input type="submit" value="Log in"><br>
<span>New User?  </span><a href="register.jsp">Register here</a> <br><br>
<span>Find existing users from </span><a href="find.jsp">here</a> <br>

</form>
</p>
</body>
</html>