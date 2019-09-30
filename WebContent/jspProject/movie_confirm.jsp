<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>확인 화면</title>
</head>
<body bgcolor="#FFFFFF">

<br/><br/><br/><br/><br/><br/><br/>

<table align="center" >
	<tr>
		<td align="center">
			<font face="verdana" size="7" ><b>${message1 } 확인</b></font><br/><br/>
			<font face="verdana" size="3" ><b>입력하신 정보와 일치하는 ${message1 }는 다음과 같습니다.</b></font><br/>
		</td>
	</tr>
	<tr height="15px"></tr>
</table>


<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr height="80">
		<td align="center" width="150"><font face="verdana" size="6" style="letter-spacing: 3px" color="#E98D44"><b>${message2 }</b></font></td>
	</tr>
	<tr height="20">
	</tr>
	<tr>
		<td align="center">
			<input type="button" value="${message3 }" style="text-align:center; width:244px; height:42px; letter-spacing: 5px" onclick="javascript:location.href='<%=cp%>/movie/${message4 }"/>
			<input type="button" value="${message5 }" style="text-align:center; width:244px; height:42px; letter-spacing: 5px" onclick="javascript:location.href='<%=cp%>/movie/${message6 }"/>
		</td>
	</tr>
	<tr height="40">
	</tr>
	<tr>
		<td>
			<img src="<%=cp %>/jspProject/image/findIdbase.jpg" width="1020"/>
		</td>
	</tr>
</table>



</body>
</html>