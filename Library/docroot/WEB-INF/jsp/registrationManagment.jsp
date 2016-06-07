<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<title>Registration managment page</title>
</head>
<body>

				<c:if test="${empty regList}">
					No registration requests at the moment
				</c:if>
				
				<c:if test="${not empty regList}">
				
				<table>
				<thead>
					<tr>
						<th>						
							<a href="sort.htm?sort=rg_frstNm">First name</a>
				         	<br/>				  
				         	<c:set value="${grid.sortRegFirstName}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
						</th>
				        <th>
				        	<a href="sort.htm?sort=rg_lstNm">Last name</a>
				         	<br/>				  
				         	<c:set value="${grid.sortRegLastName}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				        </th>
				        <th>				       
				        <a href="sort.htm?sort=rg_usrnm"> Username</a>
				         	<br/>				  
				         	<c:set value="${grid.sortRegUsername}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				        </th>
				        <th>Consider</th>
					</tr>
				</thead>
				<c:forEach var="reg" items="${regList}">				
				        <tr>
					       	<td>${reg.frstNm}</td>
					        <td>${reg.lstNm}</td>
					        <td>${reg.usrnm}</td>					        
					        <td>
						        <form method="post" action="singleRegistrationPage.htm">
							    <input type="hidden" name="id" value="${reg.usrId}"/>							  
								<button type="submit">Edit</button>
								</form> 
							</td>	          						                 
				        </tr>				 				            
				</c:forEach>
				<thead>		
					<tr>
						<th>
							<form action="searchFirstName.htm" method="post">
							<input style="width: 100px" type="text" name="frstNm">
							</form>
						</th>
						<th>
							<form action="searchLastName.htm" method="post">
							<input style="width: 100px" type="text" name="lstNm">
							</form>
						</th>
						<th>
							<form action="searchUsername.htm" method="post">
							<input style="width: 100px" type="text" name="usrnm">
							</form>
						</th>
					</tr>		
				</thead>	
				</table>
				
				<c:import url="/WEB-INF/jsp/pager.jsp"></c:import>

				</c:if>

</body>
</html>