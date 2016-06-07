<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/pager.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript">
$(document).ready(function(){
	$("select[name=itemsPerPage]").val(${grid.itemsPerPage});
	var page = ${grid.pageNumber};
	$("button[name=button"+page+"]").css("background-color","#127a78");
});
</script>

				<div id="pager" align="center">	
					<table>
					<tr>
					<td style="padding: 0px 0px">
						<a href="firstPage.htm">			
						<img src="<c:url value="/img/first.png"/>" height="20px" width="20px"></img>
						</a>	
					</td>
					<td  style="padding: 0px 0px">
						<a href="prevPage.htm">
						<img src="<c:url value="/img/prev.png"/>" height="20px" width="20px"></img>
						</a>	
					</td>
					<c:forEach begin="0" end="${grid.pages}" var="i">
					<td  style="padding: 0px 0px">
						<form action="customPage.htm">
							<button name="button${i}">${i+1}</button>		
							<input name="pageNumber" type="hidden" value="${i}">
						</form>
					</td>
					</c:forEach>
					<td  style="padding: 0px 0px">
						<a href="nextPage.htm">
						<img src="<c:url value="/img/next.png"/>" height="20px" width="20px"></img>
						</a>	
					</td>
					<td  style="padding: 0px 0px">
						<a href="lastPage.htm">
						<img src="<c:url value="/img/last.png"/>" height="20px" width="20px"></img>
						</a>	
					</td>
					<td>
						<form name="items" action="itemsPage.htm">						
						<select name="itemsPerPage" onchange="javascript:document.items.submit()">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
						</select>
						</form>
					</td>
					</tr>
					</table>
				</div>