<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration page</title>
</head>
<body>

<h1 align="center">Registration Page</h1>

<br/>

<form:form method="post" commandName="userModel" action="register.htm">
	<table align="center">
	    <tr>
	        <td>First name:</td>
	        <td><form:input path="firstName" /></td>
	        <td width="150px"><form:errors path="firstName" cssStyle="color:red" /></td>
	    </tr>
	    <tr>
	        <td>Last Name:</td>
	        <td><form:input path="lastName" /></td>
	        <td><form:errors path="lastName" cssStyle="color:red" /></td>
	    </tr>
	    <tr>
	        <td>Userame:</td>
	        <td><form:input path="username" /></td>
	        <td><form:errors path="username" cssStyle="color:red" /></td>
	    </tr>
	    <tr>
	        <td>Password:</td>
	        <td><form:input path="password" /></td>
	        <td><form:errors path="password" cssStyle="color:red" /></td>
	    </tr>	
	    <tr>
	        <td>Email:</td>
	        <td><form:input path="email" id="email" /></td>
	        <td><form:errors path="email" cssStyle="color:red" /></td>
	    </tr>
	    <tr>
	        <td>Administrator:</td>
	        <td><form:checkbox path="admin" /></td>
	        <td><form:errors path="admin" /></td>
	    </tr>    
	</table>
	
	<p align="center">
		<button type="submit">Register</button>		
	</p>
	<p align="center"><a href="loginPage.htm">Back</a></p>
</form:form>

<c:if test="${not empty regErr}">
<p align="center" class="error">${regErr}</p>
</c:if>

</body>
</html>