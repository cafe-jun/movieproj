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
<title>CGV+CJ ONE 통합회원 가입</title>

<script type="text/javascript" src="<%=cp %>/jspProject/js/util.js"></script>

<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		str = f.userName.value;
		str = str.trim();
		if(!str){
			alert("이름를 입력하세요!");
			f.userName.focus();
			return;
		}
		f.userName.value = str;
			
	
		str = f.userBirth.value;
		str = str.trim();
		if(!str){
			alert("생일을 입력하세요!");
			f.userBirth.focus();
			return;
		}
		f.userBirth.value = str;

		
		str = f.userTel.value;
		str = str.trim();
		if(!str){
			alert("전화번호를 입력하세요!");
			f.userTel.focus();
			return;
		}
		f.userTel.value = str;
		
		f.action = "<%=cp%>/movie/signConfirm_ok.do";
		f.submit();
		
	}

</script>

</head>
<body bgcolor="#FEF8DC">
<br/><br/><br/><br/><br/>

<font size="7"> CGV+CJ ONE 통합회원 가입</font><br/>
<font size="3"> 통합 아이디로 CJ ONE 브랜드 혜택도 받고! 포인트도 쌓고!</font><br/>

<form action="" method="post" name="myForm">
	<input type="text" name="userName" placeholder="이름을 입력해주세요" size="30"/><br/>
	<input type="text" name="userBirth" placeholder="법정생년월일 6자리를 입력해주세요." size="30"/>[YYYYMMDD]<br/>
	<input type="text" name="userTel" placeholder="휴대전화번호를 입력해주세요." size="30"/>['-'를 제외하고 입력하세요]<br/>


<input type="button" value="가입여부 확인" onclick="sendIt();"/>
<input type="button" value="회원가입" onclick="javascript:location.href='<%=cp%>/movie/signUp.do';"/>
<input type="button" value="로그인" onclick="javascript:location.href='<%=cp%>/movie/login.do';"/>

</form>

<br/>${message }

</body>
</html>