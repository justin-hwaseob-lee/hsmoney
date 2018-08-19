<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



  <head>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/ico/hsmoney_ico.png" />

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">  
   <title>HsMoney</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css"  crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"  crossorigin="anonymous">
	
	
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"  crossorigin="anonymous"></script>
	<!-- cookie -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js.cookie.js"></script>
    <!-- Custom styles for this template -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script> 
	<script src="${pageContext.request.contextPath}/resources/js/register.js"></script> 
  </head>

  <body class="text-center">
   
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}"> 
	 
	 
   <form  id="loginForm" name="loginForm" class="loginForm" method="post" action='register.do' > 
       
      <img class="mb-4" src="<%=request.getContextPath()%>/resources/images/ico/logo_hs.png" alt="" width="130" height="145"> 
      
      
       <div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="basic-addon1" style="width:47px; text-align:center;">
		    	<img  src="<%=request.getContextPath()%>/resources/images/email_icon.png" alt="" width="22" height="22"> 
			</span>
		  </div>
		  <input type="text" id="form-username" name="form-username" class="form-control" placeholder="Name" required>
		</div>
      
      
       <div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="basic-addon1" style="width:47px; text-align:center;">
		    	<img  src="<%=request.getContextPath()%>/resources/images/pw_icon.png" alt="" width="22" height="22">
		    </span>  
		  </div>
		  <input type="password" id="form-password" name="form-password" class="form-control" placeholder="Password" required   >
		</div>
		  
       
      <input type='submit' class="btn btn-lg btn-primary btn-block"  value='회원등록'>  
      <input type='button' class="btn btn-lg  btn-block"  value='로그인' onclick="window.location.href='login'"> 
      
      <p class="mt-5 mb-3 text-muted">&copy; Copyright © 2018 HsCompany.<br>All Rights Reserved.</p>
    </form>
  </body>
</html>
