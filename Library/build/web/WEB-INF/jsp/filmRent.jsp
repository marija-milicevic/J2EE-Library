<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Film rent page</title>
<link rel="stylesheet" href="css/jQueryUiCSS/jquery-ui-1.8.6.custom.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.6.custom.min.js"/>"></script>
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
						 <th>Image</th> 
				         <th>
				         	<a href="sort.htm?sort=bk_Rnttitl">Film title</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmTitle}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>

				         <th>
				         	<a href="sort.htm?sort=bk_Rntdirector">Director</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmAthrName}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th> 
                                          <th>
				         	<a href="sort.htm?sort=bk_Rntgenre">Genre</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmGenre}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
                                         <th>
				         	<a href="sort.htm?sort=bk_Rntstar">Stars</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmStar}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
                                          <th>
				         	<a href="sort.htm?sort=bk_Rntcnt">Num</a>
				         	<br/>
				         	<c:set value="${grid.sortFilmCount}" var="sortImg" scope="request"/>
				         	<c:import url="/WEB-INF/jsp/sortImages.jsp"/> 
				         </th>
				         <th>Rent</th>				         
				    </tr>
				</thead>
				<c:forEach var="film" items="${filmList}">
				        <tr>		
				          <td style="background-color: #127a78;height: 160;vertical-align: middle;padding-top: 20px">
						  <c:choose>
							<c:when test="${not empty film.img}">
								<img style="width: 135;height: 135;" src="${film.img}"></img>
							</c:when>
							<c:otherwise>
								<p align="center">No Image Available</p>
							</c:otherwise>
						  </c:choose>
				          </td>	 			          
				          <td style="vertical-align: middle">${film.titl}</td>		
				          <td style="vertical-align: middle">${film.directorName}</td>
                                          <td style="vertical-align: middle">${film.genre}</td>
                                          <td class="raiting-star">${film.star}</td>
                                          <td style="vertical-align: middle">${film.cnt}</td>
						  <td style="vertical-align: middle">
						  <form action="filmRent.htm" method="post" id="rentFilm${usr.id}">						  
						  <input type="hidden" name="usrId" value="${usr.id}">
						  <input type="hidden" name="filmId" value="${film.filmID}"> 
						  <input type="hidden" name="filmImg" value="${film.img}">   
						  <input type="hidden" name="allFilms" value="${usr.allowedFilms}">						  						  
						  <button type="submit">Rent</button>
						  </form>
						  </td>						 
				        </tr>
				</c:forEach>
				<thead>	
					<tr>
						<th style="background-color: white;"></th>
						<th>
							<form action="searchFilm.htm?retPage=filmRent" method="post">
							<input style="width: 100px" type="text" name="filmTitl">
							</form>
						</th>
						<th>
							<form action="searchDirectorFilm.htm?retPage=filmRent" method="post">
							<input style="width: 100px" type="text" name="directorNm">
							</form>
						</th>
						<th>
							<form action="searchGenre.htm?retPage=filmRent" method="post">
							<input style="width: 100px" type="text" name="genre">
							</form>
						</th>				
					</tr>		
				</thead>		
				</table>
				
				<c:import url="/WEB-INF/jsp/pager.jsp"></c:import>	
				
				</c:if>	
	
				<div id="dialog-message" title="Film rent" style="display: none;">
					<p align="center" id="err">${rentInfo}</p>	
					<p align="center">
					<c:if test="${not empty rentImg}">
					<img style="width: 135;height: 135;" src="${rentImg}"></img>
					</c:if>
				  	</p>	
				</div>				

</body>
</html>