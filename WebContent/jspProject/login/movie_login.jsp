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
<title>로그인</title>

<link rel="stylesheet" href="<%=cp%>/jspProject/css/style.css" type="text/css"/>

<script type="text/javascript" src="<%=cp %>/jspProject/js/util.js"></script>
<script type="text/javascript">

 	window.onload=function(){
		if(getCookie("userId")){
			document.myForm.userId.value=getCookie("userId");
			document.myForm.idSaveCheck.checked=true;
		}
	}
	
	 //쿠키 저장함수
	function setCookie(name, value, expiredays) {
		 var todayDate = new Date();
		 todayDate.setDate(todayDate.getDate() + expiredays);
		 document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
	}	
	
	//쿠키 불러오는 함수
	function getCookie(Name) { 
        var search = Name + "=";
        
        if (document.cookie.length > 0) { // if there are any cookies
            offset = document.cookie.indexOf(search);
        
            if (offset != -1) { // if cookie exists
                offset += search.length; // set index of beginning of value
                end = document.cookie.indexOf(";", offset); // set index of end of cookie value
        
                if (end == -1)
                    end = document.cookie.length;
                return unescape(document.cookie.substring(offset, end));
            }
        }
    }
	
	function sendIt(){
    	
		var f = document.myForm;
		
		str = f.userId.value
		str = str.trim();
		if(!str){
			alert("아이디를 입력하세요");
			f.userId.focus();
			return;
		}
		
		str = f.userPwd.value
		str = str.trim();
		if(!str){
			alert("비밀번호를 입력하세요");
			f.userPwd.focus();
			return;
		}
		
	 	if(f.idSaveCheck.checked == true){
			setCookie("userId",f.userId.value,7);
		} else {
			setCookie("userId",f.userId.value,0);
		}
	 	
	 	f.action = "<%=cp%>/movie/login_ok.do";
		f.submit();
		 
	}
	

</script>


</head>
<body style="background-color: #FDFCF0;">
<img width="100%" src="<%=cp %>/jspProject/image/loginbanner.jpg" >
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<form action="" method="post" name="myForm">
 
<table width="920px" border="1" align="center">
	<tr>
		<td colspan="2" align="left"><img src="<%=cp %>/jspProject/image/logintab.jpg">
	<tr>
		<td width="540px" align="center"><font face="돋움" size="2" >아이디 비밀번호를 입력하신 후, 로그인 버튼을 클릭해 주세요.</font></td>
		<td width="31"></td>
		<td width="349"></td>
	</tr>

	<tr>
		<td align="center"><input type="text" name="userId" placeholder="아이디를 입력하세요" size="85"/></td>
	</tr>
	<tr>
		<td><input type="password" name="userPwd" placeholder="패스워드를 입력하세요" size="85"/></td>
	</tr>
	<tr>
		<td><input type="button" value="로 그 인" class="btn2" onclick="sendIt();" /></td>
	</tr>
	
</table>


<table align="center">
	<tr>
		<td><input type="checkbox" name="idSaveCheck" />아이디 저장</td>
		<td align="right">
			<font size="2">
		 	<a href="<%=cp%>/movie/findId.do">아이디 찾기></a>
		 	<a href="<%=cp%>/movie/findPwd.do">비밀번호 찾기></a>
		 	</font>
		 </td>
	</tr>
</table>
<table align="center">
	<tr>
		<td colspan="2" align="center">${message }</td>
	</tr>
</table>


</form>

</body>
</html>