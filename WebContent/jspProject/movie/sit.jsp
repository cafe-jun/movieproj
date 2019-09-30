<%@page import="com.util.DBCPConn"%>
<%@page import="com.movie.MovieSitDAO"%>
<%@page import="com.movie.MovieSitDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");
String cp =request.getContextPath();

List<MovieSitDTO> lists=(List<MovieSitDTO>)request.getAttribute("lists"); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var result=0;
var set=1;
function select(str) {
	h="h"+str;
	if(result!=set){
		if(document.getElementById(h).value=="1"){
			 document.getElementById(str).style.backgroundColor="#98F791";
			 document.getElementById(h).value="2";
			 result++;
	 	}
		else if(document.getElementById(h).value=="2"){
			document.getElementById(str).style.backgroundColor="#444444";
			 document.getElementById(h).value="1";
			 result--;
		}else{
			alert("예약된 좌석입니다..");
		}
	}else{
		if(document.getElementById(h).value=="2"){
		document.getElementById(str).style.backgroundColor="#444444";
		 document.getElementById(h).value="1";
		 result--;
	}else{
		alert("더이상 지정할 수 없습니다.");
	}
	}
}
function resetevent(k) {
	for(i=0;i<=10;i++){
		for(j=1;j<=10;j++){
			h="h"+((i*10)+j);
			if(document.getElementById(h).value=="2"){
			document.getElementById(h).value="1";
			document.getElementById(((i*10)+j)).style.backgroundColor="#444444";
			}
		}
		set=k;
		result=0;
	}
}
function resultset() {
	if(result==set){
		var f=document.myForm;
		f.action="<%=cp%>/movie/sit_ok.do";
		alert("결제페이지로 넘어갑니다.");
		f.submit();
	}else{
		alert("인원수를 맞춰주세요.");
	}
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
<div align="center">
<br><br><br><br><br><br>
<div style="background-color: black; width: 30%; height: 70px"><br>
<font color="#ffffff" size="4"> 영화 좌석 예매</font>
</div>
<div style="border:1pt solid black; width: 30%; ">
<br>
<form action="<%=cp%>/movie/sit_ok.do" name="myForm" method="post">
<b>
<font style="font-style:inherit;">
영화명 : ${param.movietype }&nbsp;&nbsp;&nbsp; 영화 시간 : ${param.timetype } &nbsp;&nbsp;&nbsp;상영관 : ${param.roomtype }번
</font>
</b>
<br><br>
예매할 인원수 : &nbsp;&nbsp;&nbsp;
<input type="button" onclick="resetevent(1)" value="1">
<input type="button" onclick="resetevent(2)" value="2">
<input type="button" onclick="resetevent(3)" value="3">
<input type="button" onclick="resetevent(4)" value="4">
<input type="button" onclick="resetevent(5)" value="5">
&nbsp;&nbsp;
<input type="button" onclick="resultset()" value="결제페이지로">
<input type="hidden" name="movietype" value="${param.movietype }">
<input type="hidden" name="timetype" value="${param.timetype }">
<input type="hidden" name="roomtype" value="${param.roomtype }">
<br><br>
<span style="height: 10px;width: 10px;background-color: #444444"><font color="#444444">ㅁ</font> </span>예매가능
<span style="height: 10px;width: 10px;background-color: #98F791"><font color="#98F791">ㅁ</font> </span>선택한 좌석
<span style="height: 10px;width: 10px;background-color: #FF6C6C"><font color="#FF6C6C">ㅁ</font> </span>예매 불가능
<br><br>
</div>
<br><br>
<div style="background-color: gray; width: 20%; height: 20px">
screen
</div>
<br><br>
<table>
<%for(int i=0;i<10;i++){
%>
<tr>
<%for(int j=1;j<=10;j++){
	boolean result=true;
for(MovieSitDTO dto:lists){
	if(dto.getSitnum()==((i*10)+j)){
		result=false;
	}
}
if(result){
%>
<td align="center" width="30px" height="30px" bgcolor="#444444" id="<%=((i*10)+j) %>" onclick="select(this.id)"><%=((i*10)+j) %><input type="hidden" name="h<%=((i*10)+j) %>" id="h<%=((i*10)+j) %>" value="1"> </td>
<%}else{%>
<td align="center" width="30px" height="30px" bgcolor="#FF6C6C" id="<%=((i*10)+j) %>" onclick="select(this.id)"><%=((i*10)+j) %><input type="hidden" name="h<%=((i*10)+j) %>" id="h<%=((i*10)+j) %>" value="3"> </td>
<% }
if(j==3||j==7){ %>
<td align="center" width="30px" height="30px" bgcolor="#FEF8DC"></td>
<%} %>
<%} %>
</tr>
<%} %>
</table>
<br>
</form>
</div>
</body>
</html>