<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");
String cp =request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body  style="background-color: #FEF8DC">
<!-- 광고 -->
<div style="position: absolute;top:250px;left:17%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>
<div style="position: absolute;top:250px;left:75%">
<img alt="" src="<%=cp %>/jspProject/image/sideimg.BMP">
</div>

<!-- 실 내용 -->
<div align="center">
<br><br><br><br><br><br><br>
<div style="width: 40%;border-bottom:3pt solid #444444; " align="left" ><font size="13pt"><b>무비 평점</b></font></div>
<br><br><br>
<table cellpadding="10px">
<tr>
<td style="border:3pt solid #444444;width: 200px" align="center">
<a href="movie_evaluation.do?movietype=보통의연애">
<img alt="" src="<%=cp %>/jspProject/image/movie1.jpg" style="width: 250px;height: 200px"><br>
보통의연애
</a>
</td>
<td style="border:3pt solid #444444;width: 200px" align="center">
<a href="movie_evaluation.do?movietype=장사리">
<img alt="" src="<%=cp %>/jspProject/image/movie2.jpg" style="width: 250px;height: 200px"><br>
장사리
</td>
<td style="border:3pt solid #444444;width: 200px" align="center">
<a href="movie_evaluation.do?movietype=퍼펙트맨">
<img alt="" src="<%=cp %>/jspProject/image/movie3.jpg" style="width: 250px;height: 200px"><br>
퍼펙트맨
</td>
</tr>
<tr>
<td style="border:3pt solid #444444;width: 200px" align="center">
<a href="movie_evaluation.do?movietype=양자물리학">
<img alt="" src="<%=cp %>/jspProject/image/movie4.jpg" style="width: 250px;height: 200px"><br>
양자물리학
</td>
<td style="border:3pt solid #444444;width: 200px" align="center">
<a href="movie_evaluation.do?movietype=조커">
<img alt="" src="<%=cp %>/jspProject/image/movie5.jpg" style="width: 250px;height: 200px"><br>
조커
</td>
<td>
</td>
</tr>
</table>

</div>
</body>
</html>