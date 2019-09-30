<%@page import="com.movie.Movie_AppraisalDAO"%>
<%@page import="com.movie.Movie_AppraisalDTO"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="java.util.List"%>
<%@page import="com.util.Myutil"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");
String cp =request.getContextPath();
List<Movie_AppraisalDTO> lists=(List<Movie_AppraisalDTO>)request.getAttribute("lists");
int dataCount=((Integer)request.getAttribute("dataCount")).intValue();
String pageIndexList=(String)request.getAttribute("pageIndexList");
String movietype=(String)request.getAttribute("movietype");
request.setAttribute("movietype", movietype);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body style="background-color: #FEF8DC"><br><br>

<!-- 광고 -->
<div style="position: absolute;top:250px;left:11%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>
<div style="position: absolute;top:250px;left:81%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>

<!-- 실 내용 -->
<div align="center">
<%-- <img alt="" src="<%=cp %>/jspProject/image/movie1.jpg" height="600px" width="500px"> --%>

<div id="content" align="center">
	<div align="center">
		<br><br>
	</div>
	<div class="slideshow-container">
<br><br>
  <!-- Full-width images with number and caption text -->
 <%if(movietype.equals("보통의연애")){ %>
  <div class="mySlides fade">
    <img src="<%=cp %>/jspProject/image/movie1.jpg" style="width:60%">
    <div class="text">보통의연애</div>
  </div>
<%}else if(movietype.equals("장사리")){ %>
  <div class="mySlides fade">
    <img src="<%=cp %>/jspProject/image/movie2.jpg" style="width:60%">
    <div class="text">장사리</div>
  </div>

<%}else if(movietype.equals("퍼펙트맨")){ %>
  <div class="mySlides fade">
    <img src="<%=cp %>/jspProject/image/movie3.jpg" style="width:60%">
    <div class="text">퍼펙트맨</div>
  </div>
  
<%}else if(movietype.equals("양자물리학")){ %>
  <div class="mySlides fade">
    <img src="<%=cp %>/jspProject/image/movie4.jpg" style="width:60%">
    <div class="text">양자물리학</div>
  </div>
  
<%}else if(movietype.equals("조커")){ %>
  <div class="mySlides fade">
    <img src="<%=cp %>/jspProject/image/movie5.jpg" style="width:60%">
    <div class="text">조커</div>
  </div>
<%} %>
  <!-- Next and previous buttons -->
</div>


<br><br><br>

<form name="movie_form" method="post" action="<%=cp %>/movie/movie_evaluation_ok.do">
<%if(session.getAttribute("customInfo")!=null){ %>
<div style="height: 100px; width: 800px; display: inline ">
<%}else{ %>
<div style="height: 70px;width: 800px; display: none ">
<%} %>
<br>
별점
<select name="star_select">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10" selected="selected">10</option>
</select>
&nbsp;&nbsp;&nbsp;감상평  
<input type="text" name="content" style="width: 500px">
<input type="submit" value="작성하기">
</div>
</form>

<br>
<table width="900px">
<%for(Movie_AppraisalDTO dto:lists){ %>
<tr style="background-color: gray; height: 3px"><td colspan="2">
</tr>
<tr>
<td colspan="2">
<%
int k=0;
for(int i=0;i<dto.getStar_select()/2;i++){
k++;%>
<img alt="" src="<%=cp %>/jspProject/image/fullstar.png">
<%} 
if(dto.getStar_select()%2==1){
	k++;
%>
<img alt="" src="<%=cp %>/jspProject/image/halfstar.png">
<%} %>
<%
for(int i=0;k<5;k++){%>
<img alt="" src="<%=cp %>/jspProject/image/emptystar.png">
<%} %>&nbsp;&nbsp;&nbsp;


감상평 : <%=dto.getMsg() %></td></tr>
<td>작성자 : <%=dto.getUserId() %></td> <td align="right">작성날짜 : <%=dto.getWritedate() %></td>
		
</tr>			
<%} %>
<tr style="background-color: gray; height: 3px"><td colspan="2">
</table>
<%if(dataCount!=0){%>
				<%=pageIndexList%>
				<%}else{%>
				등록된 게시물이 없습니다.
			<%
			}
			%>
</div>
</body>
</html>