<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<body>
<form action="register" method="post">
<p>Create New Account</p>
<input type="text" name="firstname" placeholder="First Name" required><span class="msg"> ${fname.msg}</span><br>
<input type="text" name="lastname" placeholder="Last Name" required><span class="msg"> ${lname.msg}</span><br>
<input type="text" name="username" placeholder="Username" required><span class="msg">${uname.msg}</span><br>
<input type="text" name="email" placeholder="Email" required><span class="msg">${email.msg}</span><br>
<input type="password" name="pass" placeholder="Password" required><span class="msg">${pass.msg}</span><br>
<input type="submit" value="Register">
</form>
</body>
</html>