<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>

<h1 align="center">Welcome to the Library</h1>

<!-- Form using controller and validators(replaced with spring security)
<br/>

<form:form method="post" commandName="userModel" action="login.htm">
	<table align="center">
	    <tr>
	        <td>Username:</td>
	        <td><form:input path="username" /></td>
	        <td><form:errors path="username" cssStyle="color:red" /></td>
	    </tr>
	    <tr>
	        <td>Password:</td>
	        <td><form:password path="password" /></td>
	        <td><form:errors path="password" cssStyle="color:red" /></td>
	    </tr>
	</table>
	
	<p align="center">
		<button type="submit">Log In</button>
	</p>
</form:form>

<form method="post" action="registrationPage.htm">
	<p align="center">
		<button type="submit">Register</button>
	</p>
</form>

<p align="center" style="color:red">${info}</p>
<label title="error"></label>

 -->
 
 	<p align="center" style="position: absolute;">
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
      </font>
    </c:if>
    </p>
    
    <br/><br/><br/>
 
    <form name="form" action="<c:url value='j_spring_security_check'/>" method="POST">
      <table align="center">
        <tr><td>Username:</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>	
      </table>
      <p align="center">
		<button type="submit">Log In</button>
	  </p>
    </form>
    
    <form method="post" action="registrationPage.htm">
	<p align="center">
		<button type="submit">Register</button>
	</p>
	</form>	
    
 
</body>
</html>