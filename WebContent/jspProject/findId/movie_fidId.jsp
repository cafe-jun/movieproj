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
<title>Insert title here</title>

<script type="text/javascript">
	
	function findId(){
		
		var f = document.myForm;
		
		if(!f.userName.value){
			alert("이름을 입력하세요!");
			f.userName.focus();
			return;
		}
		
		if(!f.userBirth.value){
			alert("생년월일를 입력하세요!");
			f.userBirth.focus();
			return;
		}
		
		if(!f.userBirth.value){
			alert("전화번호를 입력하세요!");
			f.userBirth.focus();
			return;
		}
		
		f.action = "<%=cp%>/movie/findId_ok.do";
		f.submit();
		
	}

</script>

</head>
<body bgcolor="#FEF8DC">

아이디 찾기<br/>
아이디가 기억나지 않으세요?<br/>

<form action="" method="post" name="myForm">

<input type="text" name="userName" placeholder="이름을 입력해주세요" size="30"/><br/>
<input type="text" name="userBirth" placeholder="법정생년월일 6자리를 입력해주세요." size="30"/>[YYYYMMDD]<br/>
<input type="text" name="userTel" placeholder="휴대전화번호를 입력해주세요." size="30"/>['-'을 제외하고 입력하세요]<br/>

</form>

<input type="button" value="아이디 찾기" onclick="findId();"/>
<input type="button" value="취소" class="btn2" onclick="javascript:location.href='<%=cp%>/movie/login.do';"/>

</body>
</html>