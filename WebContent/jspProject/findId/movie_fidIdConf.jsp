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
<title>아이디 확인</title>
</head>
<body bgcolor="#FEF8DC">

아이디 확인<br/>
입력하신 정보와 일치하는 아이디는 다음과 같습니다.<br/>
<table width="400" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="150"><font color="red"><b>${message }</b></font></td>
	</tr>
</table>
<input type="button" value="비밀번호 찾기" onclick="javascript:location.href='<%=cp%>/movie/findPwd.do';"/>
<input type="button" value="로그인" onclick="javascript:location.href='<%=cp%>/movie/login.do';"/>


</body>
</html>