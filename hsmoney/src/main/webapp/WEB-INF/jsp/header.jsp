<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>


<!-- boot strap 부분 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" >
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

<!-- <nav class="navbar navbar-default"> -->
<nav class="navbar-default navbar-fixed-top" style="z-index:1200;">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="survey_result.do"><img alt="Brand" src="${pageContext.request.contextPath}/resources/images/ico/logo_small.png"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="survey_result.do">Survey Result<span class="sr-only">(current)</span></a></li>
        <li><a href="survey_target_page.do">Survey Target</a></li>
	  	<li><a href="survey_question.do">Survey Content</a></li>
	  	<li><a href="SurveyPreview">Survey Preview</a></li>
	  	<li><a href="logout.do">Log out</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>