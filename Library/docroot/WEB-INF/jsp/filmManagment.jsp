<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Film managment page</title>
<link rel="stylesheet" href="css/jQueryUiCSS/jquery-ui-1.8.6.custom.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.6.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/deleteFilmDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/confirmMessage.js"/>"></script>
</head>
<body>

				<c:if test="${empty filmList}">
					No films available
				</c:if>
				<c:if test="${not empty filmList}">
				<table>
				<thead>
					<tr>
				         <th>
				         	<a href="sort.htm?sort=bk_titl">Film title</a>
				         	<br/><br/>
				         	<c:set value="${grid.sortFilmTitle}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
                                         <th>
				         	<!--<a href="sort.htm?sort=bk_directorName">Director</a>-->
                                                <a href="sort.htm?sort=bk_director">Director</a>
				         	<br/><br/>
				         	<c:set value="${grid.sortFilmAthrName}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/>
				         </th>
                                         <th>
				         	<a href="sort.htm?sort=bk_genre">Genre</a>
				         	<br/><br/>
				         	<c:set value="${grid.sortFilmGenre}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
                                         <th>
				         	<a href="sort.htm?sort=bk_star">Stars</a>
				         	<br/><br/>
				         	<c:set value="${grid.sortFilmStar}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
                                         <th>				         
				         	<a href="sort.htm?sort=bk_updtUsrId">User updated</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmUpdateUser}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>
				         	<a href="sort.htm?sort=bk_updtDt">Date updated</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmUpdateDate}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>
				         	<a href="sort.htm?sort=bk_img">Image</a>
				         	<br/><br/>
				         	<c:set value="${grid.sortFilmImage}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>
				         	<a href="sort.htm?sort=bk_cnt">Num Available</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmCount}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         
				         <!--<th>				         
				         	<a href="sort.htm?sort=bk_creatUsrId">User entered</a>
				         	<br/>
				         	<%-- <c:set value="${grid.sortBookCreateUser}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> --%>
				         </th>
				         <th >
				         	<a href="sort.htm?sort=bk_creatDt">Date entred</a>
				         	<br/>
				         	<%--<c:set value="${grid.sortBookCreateDate}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> --%>
				         </th>-->
				         
				         <th>Update</th>
				         <th>Delete</th>
				    </tr>
				</thead>				
				<c:forEach var="film" items="${filmList}">
				        <tr>
				          <td>${film.titl}</td>
                                          <td>${film.directorName}</td>
                                          <td>${film.genre}</td>
                                          <td class="raiting-star">${film.star}</td>
                                          <td>${film.updtUsr}</td>	
				          <td>${film.updtDt}</td>
				          <td>
				          	<c:choose>
				          		<c:when test="${empty film.img}">
				          		<img src="img/cross.JPG"/>
				          		</c:when>
				          		<c:otherwise>
				          		<img src="img/tick.JPG"/>
				          		</c:otherwise>
				          	</c:choose>
				          </td>
				          <td>${film.cnt}</td>				          
				         
				          <!--<td><%--${film.creatUsr}--%></td>	
				          <td><%--${filmk.creatDt}--%></td>-->				          
				          <td>
						    <form method="post" action="singleFilmPage.htm?isNew=false">
							<input type="hidden" name="filmID" value="${film.filmID}"/>							  
							<button type="submit">Update</button>
							</form> 
						  </td>
						  <td>
						    <form method="post" id="deleteFilm" action="deleteFilm.htm">
							<input type="hidden" name="filmID" value="${film.filmID}"/>							  
							<button name="delete${film.filmID}" type="submit">Delete</button>
							</form> 
						  </td>
				        </tr>				        
				</c:forEach>		
				<thead>		
					<tr>
						<th>
							<form action="searchFilm.htm?retPage=filmManagment" method="post">																 
							<input style="width: 86px" type="text" name="filmTitl">
							</form>
						</th>
                                                <th>
							<form action="searchDirectorFilm.htm?retPage=filmManagment" method="post">
							<input style="width: 86px" type="text" name="directorNm">
							</form>
						</th>	
						<th>
							<form action="searchGenre.htm?retPage=filmManagment" method="post">
							<input style="width: 86px" type="text" name="genre">
							</form>
						</th>
											
					</tr>		
				</thead>		
				</table>						
				
				<c:import url="/WEB-INF/jsp/pager.jsp"></c:import>		
				
				</c:if>	
                                <p>
				<a href="singleFilmPage.htm?isNew=true">Add film <img src="<c:url value="/img/plus.gif"/>"></a>
				</p>
				
				<div id="dialog-message" title="Delete film">
					<p align="center" id="err">${deleteFilmErr}</p>	
				</div>						
				
				<div id="dialog-confirm" title="Delete film ?" style="display: none;"> 
					<p>
						<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
						Film will be permanently deleted and cannot be recovered. Are you sure?
					</p>
				</div>

</body>
</html>