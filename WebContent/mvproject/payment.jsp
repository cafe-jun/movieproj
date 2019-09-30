<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	int count = 0;
%>
<!DOCTYPE html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>스토어,cgv</title>

<!-- Bootstrap core CSS -->
<link href="<%=cp%>/mvproject/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=cp%>/mvproject/vendor/bootstrap/css/scrolling-nav.css" rel="stylesheet">
<script type="text/javascript">
	function paymentSendIt() {
		var f = document.myForm;
		f.action = "<%=cp%>/store/bill.do";
		alert("결제를 진행할까요 ?");
		f.submit();
	}
	function CheckAll(chk) {
		for (i = 0; i < chk.length; i++) {
			if (chk[i].checked == true) {
				chk[i].checked = false;
			} else {
				chk[i].checked = true;
			}
		}
	}
</script>
</head>
<body id="page-top" style="background-color: #FEF8DC">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top">CGV</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#about">영화</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">스토어</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">로그인</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="bg-primary text-white" style="background-image: url('<%=cp%>/mvproject/image/bg.png');" >
		
		<div class="container text-center" style="height: 500px;">
			<h1>
				<b>CGV</b> STORE
			</h1>
			<p style="font-size: 40px;">
				<b>CGV</b>&nbsp;STORE
			</p>
			<p class="lead">더 즐거운 영화감상을 하는 방법!</p>
		</div>
	</header>
	<div style="height: 50px;"></div>
		<div class="container" style="padding-left: 150px">
			<table style="width: 800px;" style="background-color: #FEF8DC">
				<tr><td style="font-size: 20px;">구매상품정보</td></tr>
				<tr><td style="height: 20px;"></td></tr>
				<tr height="3"><td colspan="8" bgcolor="black"></td></tr>
				<tr height="50">
					<td align="center" width="80">상품명</td>
					<td align="center" width="80">판매금액</td>
					<td align="center" width="60">수량</td>
					<td align="center" width="60">구매금액</td>
					<td align="center" width="60">상품삭제</td>
				</tr>
				<c:if test="${orderlists!=null}">
					<c:forEach var="dto" items="${orderlists}">
						<tr height="2"><td colspan="8" bgcolor="#cccccc"></td></tr>
							<tr height="30">
								<td align="center" bgcolor="#FEF8DC" width="50"><a href="<%=cp%>/store/storeInfo.do?num=${dto.num}"> 
								<img class="card-img-top" src="${imagePath}/${dto.saveFileName}" width="250" height="150">${dto.subject}</a></td>
								<td align="center" bgcolor="#FEF8DC" width="80">${dto.price}원</td>
								<td align="center" bgcolor="#FEF8DC" width="60">${dto.amount}개</td>
								<td align="center" bgcolor="#FEF8DC" width="60">${dto.storeSum}원</td>
								<td align="center" bgcolor="#FEF8DC" width="60"><a type="button"  href="javascript:location.href='<%=cp%>/store/storeDeleteDate.do?order_number=${dto.order_number}'" >상품삭제</a> </td>
							</tr>
					</c:forEach>
				</c:if>
				<c:if test="${orderlists == null}">
					주문한 상품이 없습니다.
				</c:if>
				<tr height="1"><td colspan="8" bgcolor="black"></td></tr>
			</table >	
			<table style="height: 50pt; margin-left: 50px;" ></table>
				<table style="width: 800px;">
					<tr><td style="height: 20px;"></td></tr>
		
					<tr height="3"><td colspan="5" bgcolor="#cccccc"></td></tr>
					<tr height="50">
						<td align="center" width="80">총 상품 금액</td>
						<td align="center" width="50"></td>
						<td align="center" width="80">할인금액</td>
						<td align="center" width="50"></td>
						<td align="center" width="100">총 결제 예정 금액</td>
					</tr>
					<tr height="2"><td colspan="5" bgcolor="#cccccc"></td></tr>
					<tr height="150">
						<td align="center" width="80" style="font-size: 30px;">${totalSum }원</td>
						<td align="center" width="50"><img alt="" src="<%=cp %>/mvproject/image/-.png" ></td>
						<td align="center" width="80" style="font-size: 30px;">0원</td>
						<td align="center" width="50"><img alt="" src="<%=cp %>/mvproject/image/=.png"></td>
						<td align="center" width="100" style="font-size: 30px; color: red;">${totalSum }원</td>
					</tr>
					<tr height="1"><td colspan="5" bgcolor="#cccccc"></td></tr>
				</table >
				<table style="width: 800px;">
					<tr>
						<td style="font-size: 20px;" colspan="4">주문자 정보 확인</td>
					</tr>
					<tr><td style="height: 20px;"></td></tr>
					<tr height="3">
						<td colspan="4" bgcolor="black"></td>
					</tr>
					<tr height="100">
						<td align="center" width="100" >이름</td>
						<td style="padding-left: 5px; " width="150"><input type="text" name="name"
							size="10" maxlength="10" class="txtField" value="${ticketdto.userName }"></td>
						<td align="center" width="100" >전화번호</td>
						<td style="padding-left: 5px;"><input type="text" name="tel"
							size="10" maxlength="30" class="txtField" value="${ticketdto.userTel }" ></td>	
					</tr>
					<tr height="1">
						<td colspan="4" bgcolor="#cccccc"></td>
					</tr>
					<tr><td colspan="4" style="font-size: 15px;">구매하신 CGV 기프트콘은 주문자 정보에 입력된 휴대전화 번호로 MMS로 발송됩니다.
					입력된 휴대전화 번호가 맞는지 꼭 확인하세요.</td></tr>
				</table>
				<table style="height: 50pt;"></table>
					<form action="" method="post" name="myForm">
						<table style="width: 800px;" border="0">
							<tr>
								<td style="font-size: 20px;" colspan="4">결제 수단</td>
							</tr>
							<tr><td style="height: 20px;"></td></tr>
							<tr height="2"><td colspan="4" bgcolor="black"></td></tr>			
							<tr height="100" style="margin-left: 20px;">
								<td colspan="2" >
									<input type="radio" name="pay" checked="checked"/>
								    <font>카드</font>
										<select name="cardType">
											<option value="국민카드" >카드를 선택하세요</option>
											<option value="KB카드">KB카드</option>
											<option value="BC카드">BC카드</option>
											<option value="삼성카드">삼성카드</option>
											<option value="신한카드">신한카드</option>
											<option value="외환카드">외환카드</option>
											<option value="하나카드">하나카드</option>
											<option value="롯데카드">롯데카드</option>
											<option value="씨티(한미)카드">씨티(한미)카드</option>
											<option value="광주카드">광주카드</option>
											<option value="현대카드">현대카드</option>
											<option value="전북카드">전북카드</option>
											<option value="NH카드">NH카드</option>
											<option value="수협카드">수협카드</option>
											<option value="제주카드">제주카드</option>
											<option value="신협체크카드">신협체크카드</option>
											<option value="우리(평화)카드">우리(평화)카드</option>
											<option value="카카오페이">카카오페이</option>			
										</select>
									</td>
								</tr>	
								<tr height="2">
									<td colspan="4" bgcolor="#cccccc"></td>
								</tr>
								<tr height="2">
									<td colspan="4" bgcolor="#cccccc"></td>
								</tr>
								<tr>
									<td colspan="4" style="font-size: 15px;"><font color="gray">카카오페이는
											신용카드 선할인과 카드사 포인트는 이용하실 수 없으며 신용카드별 청구 할인은 이용하실 수 있습니다.</font></td>
								</tr>
							</table>
							<input type="hidden" name="num" value="${dto.num}"/>
							<input type="hidden" name="storeSum" value="${storeSum}"/>	
						</form>	
						<table style="height: 50pt;"></table>
						<form name="fob">
						<table style="width: 800px; margin-left: 50px; height: 80px;">
							<tr style="margin-left: 20px;">
								<td><input type="checkbox" onclick="CheckAll(document.fob.aaa)" />
								<font size="4pt;">&nbsp;주문정보/결제 대행 서비스 약관 모두 동의</font></td>
								</tr>
						</table>
						<table style="width: 800px; margin-left: 50px; background-color: #FEF8DC;" border="0">
							<tr height="20">
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="aaa"/>
								<font size="4pt;">&nbsp;기프티콘 구매 동의</font></td>
							</tr>
							<tr height="20">
								<td style="font-size: 13px;"><font color=#5E5E5E>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										기프트콘 발송 및 CS처리 등을 이해 수신자로부터 CJCGV에 수신자의 휴대전화번호를 제공하는 것에 대한 적합한 동의를
										받습니다.</font>
								<td>
							</tr>
							<tr height="20"><td colspan="4" ></td></tr>
							<tr height="2"><td colspan="4" bgcolor="#cccccc"></td></tr>
							<tr height="20"><td colspan="4" ></td></tr>
							<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="aaa"/>
							<font size="4pt;">&nbsp;결제대행 서비스약관 모두 동의</font></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"	name="aaa"/>
								<font size="4pt;">&nbsp;전자금융거래 이용약관</font></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="aaa" />
								<font size="4pt;">&nbsp;개인정보제공 및 위탁안내</font></td>
							</tr>
							<tr height="20">
								<td></td>
							</tr>
						</table>
						</form>
						<table style="height: 50pt;"></table>
						<table style="width: 800px; margin-left: 50px;">
							<tr align="center">
								<td><input type="button" value="결제하기" class="btn btn-primary" onclick="paymentSendIt();"/></td>
							</tr> 
						</table>					
					</div>
				<br/><br/>
				<section id="contact">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 mx-auto">
							<h2></h2>
							<p class="lead"></p>
						</div>
					</div>
				</div>
			</section>
	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
		<!-- container -->
			<p class="m-0 text-center text-white">Copyright&copy;Your
				Website 2019</p>
		</div>
	</footer>
		<!-- Bootstrap core JavaScript -->
		<script src="<%=cp%>/mvproject/vendor/jquery/jquery.min.js"></script>
		<script src="<%=cp%>/mvproject/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Plugin JavaScript -->
		<script src="<%=cp%>/mvproject/vendor/jquery-easing/jquery.easing.min.js"></script>
	
		<!-- Custom JavaScript for this theme -->
		<script src="js/scrolling-nav.js"></script>
	</body>
</html>