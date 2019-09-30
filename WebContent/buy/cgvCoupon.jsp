<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 그 이상의 감동. CGV(결제창)</title>

<style type="text/css">

a { text-decoration: none}

.nav {

	text-align: center;
}

#content {
	
	width: 1000px;
	height: 1500px;
	margin-left: 400px;
	margin-right: 500px;
}

#header { 
	
	
 	width: 1000px;
	height: 130px; 
	padding-left: 300px;
	padding-right: 300px; 

}

.head {
	/*파이팅~~~~~ head 문제 같음ㅋㅋㅋ*/
	width: 1370px;
	height: 130px; 
	padding-left: 550px;
	margin-left: 0px;
	margin-right: 0px; 
}

.login{
	
	font-size: 9pt;

}
#test_btn1{
border-radius: 5px;
border: 1px solid #5a5a5a;
background-color: rgb(242,240,229);
color: #5a5a5a;
padding: 5px;
}

.floating{
position: fixed;
right: 50%;
top: 180px;
margin-right: -720px;
text-align: center;
width: 180px;

}

.btn2{
border: 1px;

}

.btn3{
border: 1px solid;
border-color: rgb(146,111,96);
background-color: rgb(146,111,96);
color: white;
border-radius: 5px;
height: 28px;
}

.btn4{
border: 1px solid;
border-color: rgb(130,137,142);
background-color: rgb(130,137,142);
color: white;
border-radius: 5px;
height: 28px;
}

</style>

<script type="text/javascript">

	function showHide(id) {

		var obj = document.getElementById(id);

   		 if (obj.style.display == 'none'){
   	    	 obj.style.display = 'block';
   	 	}else{
      		  obj.style.display = 'none';
   	 	}

	}
	
	function chkBoxCheck(chknum) {

	   // 체크박스 갯수만큼 반복문을 돌면서
	   for (j = 0; j < 2; j++) {

	        // 체크박스가 선택되어 있으면
	        if (eval("document.myForm.pay[" + j + "].checked") == true) {

	             // 우선 체크박스의 속성을 선택되지 않음으로 하고
	             document.myForm.pay[j].checked = false;

	           // 사용자가 클릭한 체크박스와 프로그래밍적으로 돌고 있는
	           // 체크박스의 번호가 같으면
	            if (j == chknum) {

	                  // 이런 경우만 체크박스가 선택되도록 한다...
	                  document.myForm.pay[j].checked = true;
	        	}
	    	}
		}
	}

</script>

</head>
<body leftmargin="0" rightmargin="0" bgcolor="#fef8dc">

<div class="floating">
	<div>
		<dl style="background-color: white; color: #5a5a5a; height: 35px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-bottom: -20px; border: 1px solid #5a5a5a;"><b>결제하실 금액</b></dl>
		<dl style="background-color: rgb(71,71,71); color: white; height: 50px; text-align: right; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; margin-bottom: -20px; font-size: 15pt;" ><b>결제금액EL&nbsp;원&nbsp;</b></dl>
	</div>
<br/><br/>
	<div>
		<dl style="background-color: rgb(217,231,235); color: #5a5a5a; height: 25px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-bottom: -20px; border: 1px solid #5a5a5a;"><b>할인내역</b></dl>
		<dl style="background-color: white; color: #5a5a5a; height: 35px; border: 1px solid #5a5a5a; margin-bottom: -20px;"><b>총 할인금액</b></dl>
		<dl style="background-color: rgb(60,70,79); color: rgb(137,229,255); height: 50px; text-align: right; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; margin-bottom: -20px; font-size: 15pt;" ><b>할인금액EL&nbsp;원&nbsp;</b></dl>
	</div>
<br/>
	<div>
		<dl style="background-color: rgb(240,235,210); color: #5a5a5a; height: 25px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-bottom: -20px; border: 1px solid #5a5a5a;"><b>결제내역</b></dl>
		<dl style="background-color: white; color: #5a5a5a; height: 35px; border: 1px solid #5a5a5a; margin-bottom: -20px;"><b>남은 결제금액</b></dl>
		<dl style="background-color: rgb(67,48,40); color: rgb(255,229,107); height: 50px; text-align: right; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; margin-bottom: -20px; font-size: 15pt;" ><b>결제-할인EL&nbsp;원&nbsp;</b></dl>
	</div>
</div>

<!-- header부분 -->

<div class="login" style="margin-left:350px; width: 1000px; height: 20px;" align="right">
		<a href="<%=cp%>/movie/login.do" style="color: black">로그인</a>
		&nbsp;
		<a href="<%=cp %>/movie/signConfirm.do" style="color: black">회원가입</a>
		&nbsp;
		<a href="<%=cp%>/movie/myCgv.do" style="color: black">My CGV</a>
	</div>
<img src="<%=cp %>/movie/data/image/Gline.png">
<div class="head" style="background-color: #FEF8DC; margin-right: 0px;">
	<div>
			<div style="display: inline-block; float: left; width: 20%" >
				<h1 align="left" >
				<a href="<%=cp%>/movies/cgvMain.do"><img style="padding-left: 20px;" src="<%=cp %>/movie/data/image/cgvLogo.png"></a>
				</h1>
			</div>
			<div style="display: inline-block; margin-top:10px; float: left; width: 80%" > 
				<h3 align="left" style="width: 550px; margin-left: 50px; margin-right: 0;">
					<img src="<%=cp %>/movie/data/ticket.png">
				</h3>
				<div id="nav" align="left" style="margin-right: 350px;">
				<a href="<%=cp %>/cgv/movie.do" style="color: black"><b>영화</b></a>&nbsp;&nbsp;&nbsp;
				<a href="<%=cp %>/cgv/booking.do" style="color: black"><b>예매</b></a>&nbsp;&nbsp;&nbsp;
				<a href="<%=cp %>/cgv/theater.do" style="color: black"><b>극장</b></a>&nbsp;&nbsp;&nbsp;
				<a href="<%=cp %>/cgv/event.do" style="color: black"><b>스토어</b></a>
				<br><br>
				</div>
			</div>	
	</div>
</div>
<img src="<%=cp %>/movie/data/image/Gline.png">
<br/><br/><br/><br/>

<!-- 결제창 -->

<table width="800" align="center">
<tr align="right">
	<td align="right">
		<input type="button" id="test_btn1" value="예매 다시하기" onclick="javascript:location.href='<%=cp%>';"/>
	</td>
</table>

<br/>

<table width="800" align="center" cellpadding="0" cellspacing="0" style="background-color: rgb(242,240,229);">

<tr style="background-color: rgb(51,51,51); color: white;" height="40" >
	<td align="left"><b>&nbsp;&nbsp;STEP 1.</b></td>
	<td align="right"><a onclick="location.reload();" style="color: white; cursor: pointer;" type="reset"><b>다시하기&nbsp;&nbsp;</b></a></td>
</tr>

<tr height="3">
	<td colspan="2" bgcolor="#fef8dc"></td>
</tr>

<tr style="color: rgb(51,51,51); background-color: rgb(223,222,214)" height="50">
	<td align="left">
		<b>&nbsp;&nbsp;할인쿠폰</b>
	</td>
	<td align="right">
		<a onclick="showHide('coupon')" onfocus="this.blur()">
			<img src="<%=cp%>/movie/data/under_1.png" width="40" height="40"/>&nbsp;&nbsp;
		</a>
	</td>
</tr>

</table>

<table id="coupon" width="800" align="center" cellpadding="0" cellspacing="0" style="background-color: rgb(242,240,229);">

	<tr height="30">
		<td style="background-color: rgb(249,248,243); color: rgb(102,102,102); text-align: center; font-size: 10pt; font-family: 고딕체; border: 1px solid; border-color: rgb(223,222,214);" align="center" colspan="2" width="800" height="30">
			<b>선택 불가능한 할인 쿠폰 항목은 비활성화처리됩니다.</b>
		</td>
	</tr>
	
<%-- 	<tr height="50">
	<%if(==cgv){ %>
	<%}else if(==cjone){ %>
	<td rowspan="2">             </td>
	<%} %> --%>
	
	<tr height="50" style="border: 1px solid; border-color: rgb(223,222,214);">
		<td width="200" style="border: 1px solid; border-color: rgb(223,222,214); cursor: pointer;" onclick="">
		
			<b>&nbsp;CGV 할인쿠폰</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="<%=cp%>/movie/data/thenext2.png">
		</td>
		<td style="border: 1px solid; border-color: rgb(223,222,214); background-color: rgb(242,240,228);" rowspan="2"> 
			<table width="600" align="center" cellpadding="0" cellspacing="0" style="background-color: rgb(242,240,228); border: 1px;">
<tr height="50">
	<td colspan="2" style="color: rgb(48,103,145); font-family: 굴림;"><b>&nbsp;&nbsp;●CGV 할인쿠폰</b></td>
	<td align="right">
		<input type="button" class="btn3" value="등록하기" onclick=""/>
		<input type="button" class="btn4" value="전체 쿠폰" onclick=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
</tr>
<tr>
	<td width="200" style="font-size: 10pt; color: rgb(102,102,102); font-family: 굴림;" align="left" height="30">&nbsp;&nbsp;&nbsp;&nbsp;사용가능 쿠폰</td>
	<td width="200" style="font-size: 10pt; color: rgb(102,102,102); font-family: 굴림;" align="center" height="30">&nbsp;&nbsp;&nbsp;&nbsp;쿠폰 번호</td>
	<td width="200" style="font-size: 10pt; color: rgb(102,102,102); font-family: 굴림;" align="left" height="30">&nbsp;&nbsp;&nbsp;&nbsp;유효기간</td>
</tr>
<tr height="2">
	<td colspan="3" style="background-color: rgb(223,222,214);"></td>
</tr>

<tr>
	<td align="center" colspan="3" height="300" style="font-family: 굴림; color: rgb(102,102,102); font-size: 11pt;">사용 가능한 CGV 할인쿠폰이(가) 없습니다.</td>
	<%-- <%if() {%><td>사용 가능한 CGV 할인쿠폰이(가) 없습니다.</td>
	<%}else if(){ %><td>어쩌고 저쩌고</td> <%} %> --%>
</tr>

<tr height="2">
	<td colspan="3" style="border-style: dotted; border-color: rgb(223,222,214);"></td>
</tr>

<tr>
	<td colspan="3" align="right" height="30" style="color: rgb(102,102,102); font-family: 굴림;">할인금액:&nbsp;&nbsp;&nbsp;&nbsp;할인금액EL 원</td>
</tr>

</table>
		</td>
	</tr>
	<tr height="50">
		<td width="200" style="border-top: 1px solid; border-right: 1px solid; border-top-color: rgb(223,222,214); border-right-color: rgb(223,222,214); cursor: pointer;" onclick="location.href='<%=cp%>/movies/cjone.do'">
			<b>&nbsp;CJ ONE 할인쿠폰</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="<%=cp%>/movie/data/thenext2.png">
		</td>
		<td style="border: 1px solid; border-color: rgb(223,222,214);">             </td>
	</tr>
	
	<tr height="3">
		<td colspan="2" style="background-color: rgb(242,240,229)"></td>
	</tr>
	
	<tr height="40" style="background-color: rgb(254,248,220);"></tr>

</table>

<table width="800" align="center" cellpadding="0" cellspacing="0" bgcolor="#FEF8DC">

<tr style="background-color: rgb(51,51,51); color: white;" height="40" >
	<td align="left"><b>&nbsp;&nbsp;STEP 2.</b></td>
	<td align="right"><a onclick="location.reload();" style="color: white; cursor: pointer;" type="reset"><b>다시하기&nbsp;&nbsp;</b></a></td>
</tr>

<tr height="3">
	<td colspan="2" style="background-color: rgb(242,240,229)"></td>
</tr>

<tr style="color: rgb(51,51,51); background-color: rgb(223,222,214)" height="50">
	<td align="left">
		<b>&nbsp;&nbsp;관람권/기프티콘</b>
	</td>
	<td align="right">
			<img src="<%=cp%>/movie/data/under_1.png" width="40" height="40"/>&nbsp;&nbsp;
		</a>
	</td>
</tr>

<tr height="40" style="background-color: rgb(223,222,214);"></tr>

</table>

<table width="800" align="center" cellpadding="0" cellspacing="0" bgcolor="#FEF8DC">

<tr height="3">
	<td colspan="2" style="background-color: rgb(242,240,229)"></td>
</tr>

<tr style="background-color: rgb(51,51,51); color: white;" height="40" >
	<td align="left"><b>&nbsp;&nbsp;STEP 3.</b></td>
	<td align="right"><a onclick="location.reload();" style="color: white; cursor: pointer;" type="reset"><b>다시하기&nbsp;&nbsp;</b></a></td>
</tr>

<tr height="3">
	<td colspan="2" style="background-color: rgb(242,240,229)"></td>
</tr>

<tr style="color: rgb(51,51,51); background-color: rgb(223,222,214)" height="50">
	<td align="left">
		<b>&nbsp;&nbsp;포인트 및 기타결제 수단</b>
	</td>
	<td align="right">
			<img src="<%=cp%>/movie/data/under_1.png" width="40" height="40"/>&nbsp;&nbsp;
		</a>
	</td>
</tr>

<tr height="40" style="background-color: rgb(223,222,214);"></tr>

</table>

<table width="800" align="center" cellpadding="0" cellspacing="0" bgcolor="#FEF8DC">

<tr height="3">
	<td colspan="2" style="background-color: rgb(242,240,229)"></td>
</tr>

<tr style="background-color: rgb(51,51,51); color: white;" height="40" >
	<td align="left"><b>&nbsp;&nbsp;STEP 4.</b></td>
	<td align="right"><a onclick="location.reload();" style="color: white; cursor: pointer;" type="reset"><b>다시하기&nbsp;&nbsp;</b></a></td>
</tr>

<tr height="3">
	<td colspan="2" style="background-color: rgb(242,240,229)"></td>
</tr>

<tr style="color: rgb(51,51,51); background-color: rgb(223,222,214)" height="50">
	<td align="left">
		<b>&nbsp;&nbsp;최종결제 수단</b>
	</td>
	<td align="right">
	</td>
</tr>

</table>

<form action="" name="myForm">

<table width="800" align="center" cellpadding="0" cellspacing="0" bgcolor="#FEF8DC">
<tr> 
	<td style="font-family: 굴림; background-color: rgb(242,240,229);" height="50">
		<input type="checkbox" name="pay" value="신용카드" onclick="chkBoxCheck(0); javascript:location.href='<%=cp%>/movies/card.do';"/><b>신용카드&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<input type="checkbox" name="pay" value="휴대폰 결제" onclick="chkBoxCheck(1); javascript:location.href='<%=cp%>/movies/phone.do';"/><b>휴대폰 결제</b>
	</td>
</tr>

<!-- <tr>
	<td width="20"></td>
	<td style="border-bottom: 2px solid; border-bottom-color: rgb(223,222,214);">
	<td width="20"></td>
</tr> -->
</table>

</form>

<form action="" name="myForms">

<table bordercolor="white" align="center" style="background-color: rgb(29,29,28);" width="1920" height="150">
<tr height="8">
	<td></td>
</tr>
<tr align="center">
	<td width="715" align="left"></td>
	<td width="100" align="left">
		<a href=""><img alt="" src="<%=cp%>/movie/data/selectseat.png"></a>
	</td>
	<td width="200" style="color: rgb(204,204,193); border-right: 1px solid; font-size: 12pt;"><b>영화제목EL</b></td>
	
	<td width="50" style="color: rgb(204,204,193); text-align: right; font-size: 10pt;">
		극장<br/>일시<br/>인원
	</td>
	<td width="150" style="color: rgb(204,204,193); border-right: 1px solid; font-size: 10pt"><b>극장EL<br/>일시EL<br/>인원EL</b></td>
	
	<td width="50" style="color: rgb(204,204,193); text-align: right; font-size: 10pt">
		좌석<br/>번호
	</td>
	<td width="150" style="color: rgb(204,204,193); font-size: 10pt"><b>EL</b></td>
	<td width="200" align="left">
		<a href="javascript:alert('결제를 진행할까요?')"><img src="<%=cp%>/movie/data/pay.png" onclick="javascript:location.href='<%=cp%>/movies/buy_ok.do';"></a>
	</td>
	
	<td width="700" align="left"></td>
<tr height="8">
	<td></td>
</tr>
</table>
<!-- 열심히 하세용 -->
</form>

<table>
<tr>
	<td>
		<img alt="" src="<%=cp%>/movie/data/undercode.png">
	</td>
</tr>
</table>

</body>
</html>