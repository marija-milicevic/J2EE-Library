<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${sortImg==0}">
		<img align="left" src="img/asc.gif"></img>
	</c:when>
	<c:when test="${sortImg==1}">
		<img align="left" src="img/dsc.gif"></img>
	</c:when>
	<c:otherwise>
		<img align="left" src="img/ascdsc.gif"></img>
	</c:otherwise>
</c:choose>