<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	request.setCharacterEncoding("UTF-8");	
	String cp = request.getContextPath();
	int count = 0;
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>스토어</title>

  <!-- Bootstrap core CSS -->
  <link href="<%=cp%>/mvproject/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="css/scrolling-nav.css" rel="stylesheet">
</head>
<body id="page-top" style="background-color: #FEF8DC">
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">CGV</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">영화</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#services">스토어</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">로그인</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <header class="bg-primary text-white" style="background-image: url('<%=cp%>/mvproject/image/bg.png')">
    <div class="container text-center" style="height: 500px;">
      <h1><b>CGV</b> STORE</h1>
      <p class="lead">더 즐거운 영화감상을 하는 방법!</p>
       <p class="lead">스토어의 다양한 상품과 함께 하세요!</p>
    </div>
  </header>
  <br/><br/>
	<div class="container" align="left">
	 	 <section id="about">
		      <div class="row">
		        <div class="col-lg-8 mx-auto" >
		          <h3 style="margin-left: -50px;"><b>팝콘/음료/굿즈</b></h3> 
		      	</div>
	      	</div>
	  </section>
	 </div> 
	  <br/>
	  <div class="container">
		  <div class="row text-center" align="right" style="width: 850px;margin-left: 150px;">	
			  <c:forEach var="dto" items="${lists}">
			      <div class="col-lg-3 col-md-6 mb-4">
			        <div class="card h-100">
			          <a href="<%=cp%>/store/storeInfo.do?num=${dto.num}"><img class="card-img-top" src="${imagePath}/${dto.saveFileName}" alt="" width="250" height="150"></a>
			          <div class="card-body">
			            <h5 class="card-title"><b>${dto.subject}</b></h5>
			            <p class="card-text" align="right">${dto.price}원</p>
			          </div>
			        </div>
			      </div>
			 </c:forEach> 
		 </div>
	  </div>	
 <section id="contact">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>Contact us</h2>
          <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero odio fugiat voluptatem dolor, provident officiis, id iusto! Obcaecati incidunt, qui nihil beatae magnam et repudiandae ipsa exercitationem, in, quo totam.</p>
        </div>
      </div>
    </div>
  </section>
  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>
  <!-- Bootstrap core JavaScript -->
  <script src="<%=cp%>/mvproject/jquery/jquery.min.js"></script>
  <script src="<%=cp%>/mvproject/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Plugin JavaScript -->
  <script src="<%=cp%>/mvproject/vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- Custom JavaScript for this theme -->
  <script src="<%=cp%>/mvproject/js/scrolling-nav.js"></script>
</body>

</html>