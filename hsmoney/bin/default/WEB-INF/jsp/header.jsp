<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500"> 



<!-- boot strap 부분 -->
 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"  crossorigin="anonymous">
<!--  jQuery 기본 js파일  
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
-->
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"  crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.number.js" > </script> 
 
 
<!--  <nav class="navbar navbar-expand-lg navbar-light "  >#FAFCFC-->
<nav class="navbar sticky-top navbar-expand-lg navbar-light" style="background-color:#FAFCFC; " > 
	  <a class="navbar-brand" href="main.do">HsMoney</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link active" href="main.do">Main </a> 
	      <a class="nav-item nav-link" href="weekly.do">Weekly<span class="sr-only">(current)</span></a>
	      <a class="nav-item nav-link" href="monthly.do">Monthly<span class="sr-only">(current)</span></a>
	       <a class="nav-item nav-link disabled" href="annually.do">Annually</a> 
	      <a class="nav-item nav-link disabled" href="logout.do">Log out</a>
	    </div>
	  </div> 
</nav>
  