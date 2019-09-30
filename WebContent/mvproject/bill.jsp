<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>스토어,cgv</title>
<!-- Bootstrap core CSS -->
<link href="<%=cp%>/mvproject/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=cp%>/mvproject/vendor/bootstrap/css/scrolling-nav.css" rel="stylesheet">
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
			<table style="width: 800px;" border="0">
				<tr><td style="font-size: 20px;"><b>상품주문정보</b></td></tr>
				<tr><td style="height: 20px;"></td></tr>
				<tr height="3"><td colspan="6" bgcolor="black" ></td></tr>
					<tr height="50">
						<td align="center" width="20">상품명</td>
						<td align="center" width="80">주문번호</td>
						<td align="center" width="30">결제금액</td>
						<td align="center" width="30">결제방식</td>
						<td align="center" width="30">결제일자</td>
					</tr>
				<tr height="2">
					<td colspan="6" bgcolor="#cccccc"></td>
				</tr>
				<c:forEach var="dto" items="${orderlists}">	
					<tr height="30">
						<td  align="center" bgcolor="#FEF8DC" width="80"><img class="card-img-top" src="${imagePath}/${dto.saveFileName}"  width="200" height="150">${dto.subject }</td> 
						<td align="center" bgcolor="#FEF8DC" width="80">${dto.order_number}</td>
						<td align="center" bgcolor="#FEF8DC" width="60">${dto.storeSum }</td>
						<td align="center" bgcolor="#FEF8DC" width="60" >${dto.cardType}</td>
						<td align="center" bgcolor="#FEF8DC" width="60">${dto.paymentDate }</td>
					</tr>
				</c:forEach>	
				<tr height="1"><td colspan="6" bgcolor="black"></td></tr>
			</table >
			<br/><br/>
			<table style="width: 800px;">
				<tr>
					<td style="font-size: 20px;" colspan="4"><b>주문자 정보 확인</b></td>
				</tr>
				<tr><td style="height: 20px;"></td></tr>
				<tr height="3">
					<td colspan="4" bgcolor="black"></td>
				</tr>
				<tr height="70">
					<td align="left" width="100" ><b>이름</b></td>
					<td style="padding-left: 5px; " width="150">
						<input type="text" name="name" size="10" maxlength="10" class="txtField" value="${ticketdto.userName }" style="border:none;background-color: #FEF8DC;">
					</td>	
				</tr>
				<tr height="100">
					<td align="left" width="100" ><b>전화번호</b></td>
					<td style="padding-left: 5px; " width="150">
						<input type="text" name="name" size="10" maxlength="10" class="txtField" value="${ticketdto.userTel }" style="border:none;background-color: #FEF8DC;">
					</td>
				</tr>
				<tr height="70">
					<td align="left" width="100" ><b>이메일</b></td>
					<td style="padding-left: 5px; " width="150">
						<input type="text" name="name" size="20" maxlength="20" class="txtField" value="${ticketdto.userEmail }" style="border:none;background-color: #FEF8DC;">
					</td>
				</tr>
				<tr height="1">
					<td colspan="4" bgcolor="#cccccc"></td>
				</tr>
				<tr><td colspan="4" style="font-size: 15px;"></td></tr>
			</table>
			<br/><br/>
			<h1 style="text-align: center;padding-right: 100px">즐거운 관람 되세요</h1>
		</div>
		<br/><br/>
	 <!-- Footer -->
 	<footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
	    </div>  
	  </footer> 
	  <!-- Bootstrap core JavaScript -->
	<script src="<%=cp%>/mvproject/jquery/jquery.min.js"></script>
	  <script src="<%=cp%>/mvproject/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	  <!-- Plugin JavaScript -->
	  <script src="<%=cp%>/mvproject/vendor/jquery-easing/jquery.easing.min.js"></script>
	  <!-- Custom JavaScript for this theme -->
	  <script src="<%=cp%>/mvproject/js/scrolling-nav.js"></script>>
	</body>
</html>