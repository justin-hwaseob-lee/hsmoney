<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<!doctype html>
<html >
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
     
    <link rel="icon" href="../../../../favicon.ico">

    <title>HsMoney</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"  crossorigin="anonymous">

    <!-- Custom styles for this template -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css"  crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/login.js" charset="utf-8"></script>
  </head>

  <body class="text-center">
   
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}">
	
	
	
    <form class="form-signin" method="post" action="login.do">
      <!-- <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
       
      <img class="mb-4" src="<%=request.getContextPath()%>/resources/images/ico/logo_hs.png" alt="" width="130" height="145"> 
      <label for="inputEmail" class="sr-only">Name</label>
      <input type="text" id="form-username" name="form-username" class="form-control" placeholder="Name" required autofocus>
      
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="form-password" name="form-password" class="form-control" placeholder="Password" required>
      
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      
      <p class="mt-5 mb-3 text-muted">&copy; Copyright © 2018 HsCompany.<br>All Rights Reserved.</p>
    </form>
  </body>
</html>
