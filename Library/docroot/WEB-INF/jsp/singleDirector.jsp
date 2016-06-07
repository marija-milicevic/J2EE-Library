<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Director create/update page</title>
<link rel="stylesheet" href="css/jQueryUiCSS/jquery-ui-1.8.6.custom.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.6.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/dateFormat.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	var dat = $("#datepicker").val();
	dat = $.format.date(dat, "yyyy-MM-dd");
	$("#datepicker").val(dat);
	var dat1 = $("#date").val();
	dat1 = $.format.date(dat1, "yyyy-MM-dd");
	$("#date").val(dat1);
});
</script>
<script type="text/javascript">
$(function() {
	$( "#datepicker" ).datepicker({ 
		autosize: true ,
		changeYear: true , 
		dateFormat: 'yy-mm-dd' , 
		yearRange: '1900:2013' });	
});
</script>

</head>
<body>

<c:if test="${!isNew}">
<h1 align="center">Director update</h1>
</c:if>

<c:if test="${isNew}">
<h1 align="center">Director create</h1>
</c:if>

<form method="post" action="singleDirector.htm">

	<table>
		<tr>
			<td>Director name: </td>
			<td><input type="text" maxlength="20" name="name" value="${snglDirector.name}"/></td>
		</tr>
		<tr>
			<td>Date of birth(yyyy-mm-dd): </td>
			<td><input type="text" maxlength="7" id="datepicker" name="dtOfBthString" value="${snglDirector.dtOfBth}"/></td>
		</tr>
	</table>
		
	<c:if test="${!isNew}">
		<input type="hidden" name="directorID" value="${snglDirector.directorID}"/>
		<input type="hidden" name="creatUsrId" value="${snglDirector.creatUsrId}"/>
		<input type="hidden" id="date" name="creatDtString" value="${snglDirector.creatDt}"/>
	</c:if>
	<input type="hidden" name="updtUsrId" value="${usr.id}"/>
	<input type="hidden" name="isNew" value="${isNew}" />
	
	<c:if test="${!isNew}">
	<p align="center"><button type="submit">Update</button></p>
	</c:if>
	
	<c:if test="${isNew}">
	<p align="center"><button type="submit">Create</button></p>
	</c:if>

</form>
	
	<p align="center"><a href="directorManagmentPage.htm">Back</a></p>
	
	<c:if test="${not empty singleDirectorErr}">
		<p class="error" align="center">${singleDirectorErr}</p>	
	</c:if>

</body>
</html>