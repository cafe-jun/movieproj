<%@page import="com.movie.Movie_countDTO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.movie.Movie_selectDAO"%>
<%@page import="com.movie.Movie_selectDTO"%>
<%request.setCharacterEncoding("UTF-8");
String cp =request.getContextPath();
List<Movie_selectDTO> lists=(List<Movie_selectDTO>)request.getAttribute("lists");
List<Movie_countDTO> listscount=(List<Movie_countDTO>)request.getAttribute("listscount");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function movieimg(str) {
	if(str=='보통의 연애'){
		document.getElementById('imagesrc').src="<%=cp %>/jspProject/image/movie1.jpg";
	}
	if(str=='양자물리학'){
		document.getElementById('imagesrc').src="<%=cp %>/jspProject/image/movie4.jpg";
	}
	if(str=='퍼펙트맨'){
		document.getElementById('imagesrc').src="<%=cp %>/jspProject/image/movie3.jpg";
	}
	if(str=='조커'){
		document.getElementById('imagesrc').src="<%=cp %>/jspProject/image/movie5.jpg";
	}
	if(str=='장사리'){
		document.getElementById('imagesrc').src="<%=cp %>/jspProject/image/movie2.jpg";
	}
}
function movietype() {
	var k=document.getElementsByName('movietype');
	for(var i=0; i<k.length; i++) {
		document.getElementById(k[i].value).style.display="none";
		if(k[i].checked)
			{
				document.getElementById(k[i].value).style.display="inline";
				//display:inline
			}
	}
}
function timetype1(str){
	var k=document.getElementsByName('timetype');
	for(var i=0; i<k.length; i++) {
		if(k[i].checked)
			{
				document.getElementsByName('roomtype').value=str; 
				 document.getElementById("roomtype").value=str;
			}
	}
}
	/*  alert(document.getElementsByName('roomtype').value); 
	 alert(document.getElementById("roomtype").value); */
function myformsubmit() {
	document.myForm.submit();
}
	 

</script>
</head>
<body style="background-color: #FEF8DC">

<!-- 광고 -->
<div style="position: absolute;top:250px;left:17%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>
<div style="position: absolute;top:250px;left:75%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>

<!-- 실 내용 -->
<br>
<br>
<br>
<div align="center">
<img alt="" src="<%=cp %>/jspProject/image/movie3.jpg" width="900" height="600" id="imagesrc" name="imagesrc">
<br><br>
<form method="post" name="myForm" action="<%=cp%>/movie/sit.do">
<table style="border:1pt solid black">
<tr >
	<td align="center" colspan="2" style="background-color: #4C4C4C;border:1pt solid black">
	<font color="#D5D5D5">
		영화 선택</font>
	</td>
	<td align="center" colspan="2" style="background-color: #4C4C4C;border:1pt solid black">
	<font color="#D5D5D5">
		상영 시간 선택
		</font>
	</td>
</tr>
<tr>
	<td width="300px">
	<br>
	<%String movename="";
	for(Movie_selectDTO dto:lists){ 
	if(!dto.getMovietype().equals(movename)){
	movename=dto.getMovietype();%>
	<input type="radio" id="movietype" name="movietype" value="<%=dto.getMovietype()%>" onclick="movieimg('<%=dto.getMovietype()%>');" checked="checked"><b><%=dto.getMovietype()%></b><br><br>
	<%}
	} %>
	</td>
	<td width="50px" onclick="movietype()">
	<img alt="" src="<%=cp%>/jspProject/image/select.png" style="width: 50px">
	</td>
	<td width="300px">
	<%
	movename="";
	int timeboolean=0;
	for(Movie_selectDTO dto:lists){ 
	if(!dto.getMovietype().equals(movename)){
	movename=dto.getMovietype();
	%>
	<div style="display: none" id="<%=dto.getMovietype()%>">
	<% }
	timeboolean++;%>
	<input type="radio" id="timetype" name="timetype" value="<%=dto.getTimetype()%>" onclick="timetype1(<%=dto.getRoomtype()%>);"><%=dto.getTimetype()%> <%=dto.getRoomtype()%>번 상영관<br><br>
	
	<%
	int fullcount=0;
	for(Movie_countDTO dtos:listscount){
		if(dtos.getMovietype().equals(dto.getMovietype())){
			fullcount=dtos.getCount();
		}
	}
	if(timeboolean==fullcount){
		timeboolean=0;%>
	</div>
	<%}
	} %>
	</td>
	<td  width="50px">
	<input type="hidden" id="roomtype" name="roomtype" value="" >
	<img alt="" src="<%=cp%>/jspProject/image/select.png" style="width: 50px"  onclick="myformsubmit();" >
	<!-- <input type="submit" value=">" > -->
	</td>
	<%-- <td>
	<%
	for(Movie_selectDTO dto:lists){ 
	%>
	<div style="display: none" id="<%=dto.getMovietype()%><%=dto.getRoomtype()%>">
	<input type="radio" id="roomtype" name="roomtype" value="<%=dto.getRoomtype()%>"><%=dto.getRoomtype()%><br>
	</div>
	<%
	} %>
	</td> --%>
	
</tr>
</table>
</form>
</div>
</body>
</html>