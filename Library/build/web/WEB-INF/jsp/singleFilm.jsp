<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Film create/update page</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/dateFormat.js"/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("select[name=name]").val("${snglFilm.directorName}");
	var dat = $("#date").val();
	dat = $.format.date(dat, "yyyy-MM-dd");
	$("#date").val(dat);
});
</script>
</head>
<body>

<c:if test="${!isNew}">	
<h1 align="center">Film update</h1>
</c:if>
<c:if test="${isNew}">	
<h1 align="center">Film create</h1>
</c:if>
<c:if test="${not empty singleFilmErr}">
    <p class="error" align="center">${singleFilmErr}</p>	
</c:if>
<form  method="post" action="singleFilm.htm">

	<table>
		<tr>
			<td>Film title: </td>
			<td><input type="text" maxlength="100" name="titl" value="${snglFilm.titl}"/></td>
		</tr>
		<tr>
			<td>Director: </td>
			<td>
			<select name="directorName">
			    <c:forEach items="${directorList}" var="director">
			        <option value="${director.name}">${director.name}</option>
			    </c:forEach>
			</select>
                        
			</td>
		</tr>
                <tr>
			<td>Genre: </td>
			<td><input type="text" maxlength="40" name="genre" value="${snglFilm.genre}"/></td>
		</tr>
                <tr>
			<td>Stars raiting: </td>
			<td>
                            <input type="text" maxlength="2" name="star" value="${snglFilm.star}"/>
			</td>
		</tr>
		<tr>
			<td>Remaining: </td>
			<td><input type="text" maxlength="3" name="cnt" value="${snglFilm.cnt}"/></td>
		</tr>
	</table>
		
	<c:if test="${!isNew}">	
		<input type="hidden" name="filmID" value="${snglFilm.filmID}"/>	
		<input type="hidden" id="date" name="creatDtString" value="${snglFilm.creatDt}"/>
		<input type="hidden" name="creatUsrId" value="${snglFilm.creatUsrId}"/>
	</c:if>		
	<input type="hidden" name="updtUsrId" value="${usr.id}"/>
	<input type="hidden" name="isNew" value="${isNew}" />
	
	<c:if test="${!isNew}">	
	<p align="center"><button type="submit">Update</button></p>
	</c:if>
	<c:if test="${isNew}">	
	<p align="center"><button type="submit">Create</button></p>
	</c:if>
	
	<input type="hidden" name="img" value="${snglFilm.img}" /> 

</form>
	
	<p align="center"><a href="filmManagmentPage.htm">Back</a></p>

	<c:if test="${not empty uploadInfo}">
		<p class="error" align="center">${uploadInfo}</p>	
	</c:if>
	
<form action="uploadFile.htm" method="post" enctype="multipart/form-data">
	<input maxlength="40" name="file" type="file"> 
	<input type="hidden" name="filmID" value="${snglFilm.filmID}"/>
	<button type="submit">Upload</button>	
</form>

	<c:choose>
		<c:when test="${empty snglFilm.img}">
			<p align="center">No image to display</p>			
		</c:when>
		<c:otherwise>
			<img src="${snglFilm.img}" width="300" height="200"></img>
		</c:otherwise>
	</c:choose>


</body>
</html>