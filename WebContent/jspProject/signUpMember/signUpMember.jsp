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
		
		str = f.userId.value;
		str = str.trim();
		if(!str){
			alert("아이디를 입력하세요!");
			f.userId.focus();
			return;
		}
		f.userId.value = str;

		
		str = f.userPwd.value;
		str = str.trim();
		if(!str){
			alert("패스워드를 입력하세요!");
			f.userPwd.focus();
			return;
		}
		f.userPwd.value = str;
		
	
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
		
		str = f.userEmail.value;
		str = str.trim();
		if(!str){
			alert("E-mail을 입력하세요!");
			f.userEmail.focus();
			return;
		}
		f.userEmail.value = str;
		
		str = f.userEmail.value;
		str = str.trim();
		if(str){
			if(!isValidEmail(str)){
			alert("정상적인 이메일을 입력하세요.");
			f.email.focus();
			return;
			}
		}
		f.userEmail.value = str;
		
		f.action = "<%=cp%>/movie/signUp_ok.do";
		f.submit();
		
	}

</script>

</head>
<body bgcolor="#FEF8DC">
<br/><br/>

<div id="bbs">
	<div id="bbs_title">
		회원가입
	</div>
	
	<form action="" method="post" name="myForm">
		
		<div id="bbsCreated">
		
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</dt>
					<dd>
						<input type="text" name="userName" size="35" maxlength="50" class="boxTF"/>
					</dd>
				</dl>
			</div>
		
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 아&nbsp;이&nbsp;디</dt>
					<dd>
						<input type="text" name="userId" size="35" maxlength="20" class="boxTF"/>
					</dd>
				</dl>
			</div>
		
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 패스워드</dt>
					<dd>
						<input type="password" name="userPwd" size="35" maxlength="20" class="boxTF"/>
					</dd>
				</dl>
			</div>
		
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 생년월일</dt>
					<dd>
						<input type="text" name="userBirth" size="35" maxlength="50" class="boxTF"/> [yyyy-mm-dd]
					</dd>
				</dl>
			</div>

		
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 휴대전화</dt>
					<dd>
						<input type="text" name="userTel" size="35" maxlength="50" class="boxTF"/>
					</dd>
				</dl>
			</div>
			
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt> 이메일</dt>
					<dd>
						<input type="text" name="userEmail" size="35" maxlength="50" class="boxTF"/>
					</dd>
				</dl>
			</div>

		</div>
		
		<div id="bbsCreated_footer">
			<input type="button" value="가입하기" class="btn2" onclick="sendIt();"/>
			<input type="reset" value="다시입력" class="btn2" onclick="document.myForm.userName.focus();"/>
			<input type="button" value="가입취소" class="btn2" onclick="javascript:location.href='<%=cp%>/movie/login.do';"/>
		</div>
	
	</form>

</div>


</body>
</html>