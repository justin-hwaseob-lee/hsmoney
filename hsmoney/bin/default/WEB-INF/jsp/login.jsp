<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 

<head>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!-- loading css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loading.css">
 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/login.js" charset="utf-8"></script>

<style> 
body{
 	padding-top:63px;
}
.highlight {
    background-color: #708EA8;
} 
table tr:hover td { border-top-color: #708EA8; border-bottom: 1px solid #708EA8; }  
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HsMoney</title>
</head>




 <body > 
	<div class="wrap-loading" id="LoadingImage"> 
   	 <div><img src="<%=request.getContextPath()%>/resources/images/sd_loading.gif" width="51" height="50"/> </div>
	</div> 
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}">
	



 
<!-- 설문 대상 명단 테이블 -->
<div class="container-fluid">
	<div class="table-responsive">
	<form method="post" id="resultForm" name="resultForm" action="sendMail.do" class="resultForm"> 

		<table id="moneyTable" class="table table-striped" style="font-size:12px;"> 
		
			<thead style="font-size:14px;">
				<tr>
					<th align='center'  style="vertical-align: middle"><label><input type="checkbox" id="checkall" name="checkall" ></label></th>
					<th scope="col" align='center' style="vertical-align: middle">Date</th>
					<th scope="col" align='center' style="vertical-align: middle">Category</th>
					<th scope="col" align='center' style="vertical-align: middle">price</th> 
				</tr>
			</thead>
			
			<tbody id="moneyListBody" align="center">  
			</tbody>  
		</table> 
	
		<nav class="navbar navbar-fixed-bottom" style="background-color:#FAFCFC; padding-bottom:15px;">
		<hr class="divider" style="margin-top:0px; margin-bottom:15px;"></hr>
			<div class="form-inline" style="padding-left: 20px; padding-right: 20px;" >
				<div class="form-group col-xs-12 col-sm-6 text-left">
					<label for="fastSearch">Fast Search</label>	<input type="text" class="form-control" id="search" name="search" onkeydown="if(event.keyCode == 13) return false;">
				</div>
				<div class="form-group col-xs-12 col-sm-6 text-right"> 
					<input class="btn btn-primary" type="button" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
				</div>
			</div>
		</nav>
	</form>
	</div> 
</div>

 
</body>
</html>