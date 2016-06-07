<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="user" class="com.uib.library.controller.UserController" scope="session"/>
<jsp:setProperty name="user" property="*"/> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
<script type="text/javascript">
window.onload=function(){
	<% String deny = request.getParameter("deny"); 
	if(deny!=null){%>
	alert("<%= deny %>");
	<%}%>
}
</script>
</head>
<body>

				<div align="center">
				<label>Welcome &nbsp; ${usr.firstName} &nbsp; ${usr.lastName}</label>
				
				<br/><br/>
				
				<c:if test="${empty listRents}">
					You have no films rented
				</c:if>
				
				<c:if test="${not empty listRents}">
				
				<table>
				<thead>
					<tr>
						<th>Title</th>
				        <th>Director</th>
				        <th>Date of rent</th>
					</tr>
				</thead>
				<c:forEach var="rent" items="${listRents}">				
				        <tr>
					       	<td>${rent.titl}</td>
					        <td>${rent.director}</td>
					        <td>${rent.dateOfRent}</td>					                 						                 
				        </tr>				 				            
				</c:forEach>
				</table>
				
				<br/><br/>
				
				<form method="post" action="filmReturn.htm">
				<button type="submit">Return all films</button>
				<input type="hidden" name="usrId" value="${usr.id}">
				</form>	

				</c:if>								
				</div>																
				
				
						
</body>
</html>