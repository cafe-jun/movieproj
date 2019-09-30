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
<title>상품페이지</title>
<link rel="stylesheet" href="<%=cp%>/mvproject//vendor/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=cp%>/mvproject/vendor/bootstrap/jquery/jquery.slim.min.js" ></script>
<script type="text/javascript" src="<%=cp%>/mvprojectvendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	
	var paysell_price;
	var basketsell_price;
	
	var amount;

	function init () {
		
		paysell_price = document.form.paysell_price.value;
		basketsell_price = document.basket.basketsell_price.value;
		
		payamount = document.form.amount.value;
		basketamount = document.basket.amount.value;
		
		document.form.storeSum.value = paysell_price;
		document.basket.storeSum.value = basketsell_price;
		
		change();
	}
	
	function add () {
		
		hmpay = document.form.amount;
		sumpay = document.form.storeSum;
		hmbasket = document.basket.amount;
		sumbasket = document.basket.storeSum;
		
		hmpay.value ++ ;
		hmbasket.value++;
		
		sumpay.value = parseInt(hmpay.value) * paysell_price;
		sumbasket.value = parseInt(hmbasket.value) * basketsell_price;
	}
	function del () {
		hmpay = document.form.amount;
		sumpay = document.form.storeSum;
		
		hmbasket = document.basket.amount;
		sumbasket = document.basket.storeSum;
			
		if (hmpay.value > 1 || hmbasket.value>1) {
				hmpay.value -- ;
				hmbasket.value -- ;
				sumpay.value = parseInt(hmpay.value) * paysell_price;
				sumbasket.value = parseInt(hmbasket.value) * basketsell_price;
			}
	}
	function change() {
		hmpay = document.form.amount;
		hmbasket = document.basket.amount;
		sumpay = document.form.storeSum;
		sumbasket = document.basket.storeSum;
			
		if (hmpay.value < 0 || hmbasket.value < 0) {
				hmpay.value = 0;
				hmbasket.value = 0;
		}
		sumpay.value = parseInt(hmpay.value) * paysell_price;
		sumbasket.value = parseInt(hmbasket.value) * basketsell_price;
	}
	
	function paymentsendIt() {
		
		var f = document.form;
		
		var session = "<c:out value='${sessionScope.customInfo.userId}'/>";
		if(session == ""){
			alert("로그인 되지 않았습니다. 로그인 해주세요 ");
			f.action = "<%=cp%>/movie/login.do";
			f.submit();
		}else{
			f.action = "<%=cp%>/store/paymentInsertData.do?num=${dto.num}";
			f.submit();		
		}
	
	}
	function bastketSendIt() {
		
		var f = document.basket;
		
		var session = "<c:out value='${sessionScope.customInfo.userId}'/>";
		if(session == ""){
			alert("로그인 되지 않았습니다. 로그인 해주세요 ");
			f.action = "<%=cp%>/movie/login.do";
			f.submit();
		}else{
			f.action = "<%=cp%>/store/paymentInsertData.do?num=${dto.num}";
			f.submit();		
		}
	
	}
	
</script>
</head>
<body style="background-color: #FEF8DC" onload="init();">
<!-- Page Content -->

<div class="container">
  <!-- Portfolio Item Heading -->
  <h1 class="my-4">상품/음료/굿즈</h1>
  <small><h3><b>상품명 : ${dto.subject}</b></h3></small>
	<br/>
  <!-- Portfolio Item Row -->
  <div class="row">

    <div class="col-md-8">
      <img class="img-fluid" src="${imagePath}/${dto.saveFileName}" style="padding-left: 50px"  width="500" height="450" alt="">
    </div>
    <div class="col-md-4" >
      <h5 class="my-3"><b>상품구성</b></h5>
      <br/>
      <h5><b>원 산 지 : 미국 </b></h5>
      <br/>
      <h5><b>유효기간 : 구매일로 부터 6개월</b></h5>
      <br/>
      <h5><b>상품 수량</b></h5>
      <form action=""  name="form" method="post">
	      	<input type= "hidden" name="paysell_price" value="${dto.price}">
			<input type="text" style="text-align: center"  name="amount" value="1" size="3" onchange="change();">
			<input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>
		      <h3 class="my-3"><b>상품가격 :</b>&nbsp;&nbsp;<b><input type="text" name="storeSum" size="4" readonly style="border:none;background-color: #FEF8DC;">원</b></h3><br/>
		      <input type="button" class="btn btn-primary" value="구매취소" style="width: 100px;height: 50px;" onclick="javascript:location.href='<%=cp%>/store/list.do';"/>
			  <input type="submit" class="btn btn-primary" value="구매하기" style="width: 100px;height: 50px;" onclick="paymentsendIt();"/>
	  </form>
	  <form action="" method="post" name="basket" >
		   <input type= "hidden" name="basketsell_price" value="${dto.price}">
		   <input type="hidden" style="text-align: center;"  name="amount" value="1" size="3" onchange="change();">
	       <input type="hidden" name="storeSum" size="4" readonly style="border:none;background-color: #FEF8DC;">
	       <!-- <input type="submit" class="btn btn-primary" value="장바구니" style="width: 100px;height: 50px;" onclick=""/>-->
	       <input type="image" src="<%=cp%>/mvproject/image/basketimage.jpg" width="70" border="0" onclick="bastketSendIt();">
	   </form> 
	  <br/><br/>	
    </div>
	  <hr width="100px" size="50"/>
  </div>
  <div class="col-md-12">
  	<h4>[이용안내]</h4>
  	<ul>
	 <li> 해당 기프트콘은 오프라인 매점에서 실제 상품으로 교환할 수 있는 온라인 상품권입니다.</li>
	 <li>구매 후 전송받으신 기프트콘은,사용가능한 CGV의 매점에서 지정된 해당 상품으로만 교환이 가능합니다.
	     (사용가능 CGV는 '상품교환'에서 확인 가능합니다
	     해당 상품 내에서 팝콘 맛 혹은 사이즈 변경 시 추가 비용 발생합니다.교환 완료한 상품의 환불 및 반품은 불가합니다.</li>
	 <li>유효기간 연장을 신청하는 경우,유효기간은 발급일로부터 5년 이내 횟수 제한 없이 기간 연장 가능하며, 1회 연장 시 3개월(92일) 단위로 유효기간이 연장됩니다.  
	     단, 이벤트 경품 및 프로모션 상품의 경우 유효기간 연장이 불가할 수 있습니다. 유효기간 만료 이후에는 결제금액의 90% 환불이 가능합니다.</li>  
	 <li>상기 이미지는 실제와 다를 수 있습니다.</li>
 	</ul>
 	<h4>[취소/환불 규정]</h4> 
 	<ul>
 		<li>구매자는 최초 유효기간 이내에 결제금액의 100%에 대해 결제취소/환불이 가능하며, 최초 유효기간 만료 후에는 수신자가 결제금액의 90%에 대해 환불 요청 가능합니다.</li>
 		<li>단, 이미 사용된 기프트콘에 대해서는 결제취소/환불 신청이 불가합니다. </li>
 	</ul>
 	<h4>[결제취소/환불 방법]</h4>
 	<ul>
 		<li>결제취소는 모바일App,웹 > MY > 결제내역조회 > 스토어 or 홈페이지 > My CGV > 스토어 > 결제내역의 해당 주문 상세내역에서 가능합니다.</li>
 		<li>기프트콘은 구매일로부터 60일 이내 결제취소 가능하며, 카드 결제취소 가능 기간이 경과하였을 경우, 고객센터로 연락주시면 됩니다)
            환불은 모바일App,웹 > MY > 기프트콘 or 홈페이지 > My CGV > 스토어 > 내 기프트콘에서 환불을 원하는 기프트콘 등록 후 진행 가능하며, 비회원의 경우 고객센터로 신청 가능합니다.</li>
 	</ul>
 	<h4>[단 이 때, 본인 확인 및 계좌 확인 절차가 진행됩니다.]</h4>
 	<ul>
 		<li>수신자는 선물받은 기프트콘의 수신거절을 요청할 수 있으며, 이 경우 구매자에게 취소 및 환불에 대한 안내가 이루어집니다.</li>
 		<li> 결제취소 가능 기간이 경과한 후 수신자가 수신거절을 요청할 경우 구매자에게 기프트콘이 재발송됩니다.</li>
 	</ul>
  </div>
  <!-- /.row -->
  <!-- Related Projects Row -->
  <h3 class="my-4"><b>이 상품과 함께 본 상품</b></h3>

  <div class="row">
  	<c:forEach var="dto" items="${lists}">
	  	<c:if test="${dto.num<=4 }">
	  	<div class="col-md-3 col-sm-6 mb-4">
	      <a href="<%=cp%>/store/storeInfo.do?num=${dto.num}">
	      	<!-- image size 500*300 -->
	        <img class="img-fluid" src="${imagePath}/${dto.saveFileName}">
	      </a>
	       <h5><b>${dto.subject}</b></h5>
	    </div>
	   </c:if> 
    </c:forEach>
  </div>
  <!-- /.row -->
</div>
<!-- /.container -->
</body>
</html>