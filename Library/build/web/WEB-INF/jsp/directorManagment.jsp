<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Director managment page</title>
<link rel="stylesheet" href="css/jQueryUiCSS/jquery-ui-1.8.6.custom.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.6.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/deleteAuthorDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/confirmMessage.js"/>"></script>
</head>
<body>

				<c:if test="${empty directorList}">
					No directors available
				</c:if>
                                <c:if test="${not empty deleteDirectorErr}">
                                    <p class="error" align="center">${deleteDirectorErr}</p>	
                                </c:if>
				<c:if test="${not empty directorList}">
                                <div class="">
				<table>
				<thead>								
					<tr>
				         <th>
				         	<a href="sort.htm?sort=at_directorName">Director name</a>
				         	<br/>				  
				         	<c:set value="${grid.sortDirectorName}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/>       	
				         </th>
				         <th>
				         	<a href="sort.htm?sort=at_dtOfBth">Date of Birth</a>
				         	<br/>
				         	<c:set value="${grid.sortDirectorDateOfBirth}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/>   
				         </th>
				         <th>
					         <a href="sort.htm?sort=at_creatUsrId">User entered</a>
					         <br/>
					         <c:set value="${grid.sortDirectorCreateUser}" var="sortImg" scope="request"/>
					         <c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>
				         	<a href="sort.htm?sort=at_creatDt">Date entred</a>
				         	<br/>
				         	<c:set value="${grid.sortDirectorCreateDate}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>				         
				         	 <a href="sort.htm?sort=at_updtUsrId">User updated</a>
				         	 <br/>
					         <c:set value="${grid.sortDirectorUpdateUser}" var="sortImg" scope="request"/>
					         <c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>
				         	<a href="sort.htm?sort=at_updtDt">Date updated</a>
				         	<br/>
				         	<c:set value="${grid.sortDirectorUpdateDate}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>Update</th>
				         <th>Delete</th>
				    </tr>
				</thead>
				<c:forEach var="director" items="${directorList}">
				        <tr>
				          <td>${director.name}</td>				          
				          <td>${director.dtOfBth}</td>
				          <td>${director.creatUsr}</td>	
				          <td>${director.creatDt}</td>
				          <td>${director.updtUsr}</td>	
				          <td>${director.updtDt}</td>
				          <td>
						    <form method="post" action="singleDirectorPage.htm?isNew=false">
							<input type="hidden" name="directorID" value="${director.directorID}"/>							  
							<button type="submit">Update</button>
						    </form> 
						  </td>
						  <td>
						    <form method="post" id="deleteDirector" action="deleteDirector.htm">
							<input type="hidden" name="directorID" value="${director.directorID}"/>							  
							<button type="submit" name="delete${director.directorID}">Delete</button>
							</form> 
						  </td>
				        </tr>
				</c:forEach>	
				<thead>		
					<tr>
						<th>
							<form action="searchDirector.htm" method="post">
							<input style="width: 100px" type="text" name="directorName">
							</form>
						</th>
					</tr>		
				</thead>							
				</table>	
				</div>
				<c:import url="/WEB-INF/jsp/pager.jsp"></c:import>	
								  			
				</c:if>	
                                <p>				
				<a href="singleDirectorPage.htm?isNew=true">Add director<img src="<c:url value="/img/plus.gif"/>"></a>
				</p>
				
				<div id="dialog-message" title="Delete director">
					<p align="center" id="err">${deleteDirectorIDErr}</p>	
				</div>					
				
				<div id="dialog-confirm" title="Delete director ?" style="display: none;"> 
					<p>
						<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
						Director will be permanently deleted and cannot be recovered. Are you sure?
					</p>
				</div>
														
</body>
</html>