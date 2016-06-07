<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<fmt:setBundle basename='english'/>
			
	<ul id="navigation">
		<li><a href="homePage.htm"><fmt:message key="menu.home"/></a></li>
		<sec:authorize ifAllGranted="administrator">
		<li><a href="regManagment.htm"><fmt:message key="menu.regs"/></a></li>
		<li><a href="directorManagment.htm"><fmt:message key="menu.dire"/></a></li>
		<li><a href="filmManagment.htm"><fmt:message key="menu.film"/></a></li>	
		</sec:authorize>
		<li><a href="filmRentPage.htm"><fmt:message key="menu.rent"/></a></li>
		<li><a href="<c:url value="/j_spring_security_logout" />"><fmt:message key="menu.lgot"/></a></li>
	</ul>