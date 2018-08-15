<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 

<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
<!-- jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!--  jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

  
<c:import url="header.jsp" />
<!-- loading css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loading.css">
 
 
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/monthly.js" charset="utf-8"></script>  

<style> 
body{ 
 	padding-bottom:111px;
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
	 
<!-- monthly 테이블 -->
<div class="container-fluid">
	<div class="table-responsive">
	<form  id="resultForm" name="resultForm"  class="resultForm"> 

		<table id="moneyTable" class="table table-striped" style="font-size:12px;"> 
		
			<thead style="font-size:14px;">
				<tr>
					<th class="text-center"  style="vertical-align: middle;"><label><input type="checkbox" id="checkall" name="checkall" ></label></th>
					<th class="text-center" style="vertical-align: middle;">Date</th>
					<th class="text-center" style="vertical-align: middle;">Category</th>
					<th class="text-center" style="vertical-align: middle;">price</th> 
				</tr>
			</thead>
			
			<tbody id="moneyListBody" align="center">  
			</tbody>  
		</table> 

		<nav class="navbar fixed-bottom sticky" style="background-color:#FAFCFC;">
		<hr class="divider" style="margin-top:0px; margin-bottom:15px;"></hr>
		 
		 	<div class="input-group mb-3"  style="padding-top: 10px;">
				  <div class="input-group-prepend">
					    <input type="text" value ="Month Total" class="input-group-text30" readonly> 
				  </div>
				  <input type="text" readonly class="form-control input-group-text" id="monthTotal" name="monthTotal" value="${monthlyTotal}" style="width:100px">
				  <div class="input-group-append">
				    <span class="input-group-text" style="">원</span>
				  </div>
			</div>
			 
			<div class="input-group mb-3 " >
			 <input id="month_standard" name="month_standard"  type="month" style="width:118px;" onchange="monthChange()">
				<input type="text" class="form-control" id="search" placeholder="Fast Search" name="search" onkeydown="if(event.keyCode == 13) return false;">
				
				<div class="col-md- text-right" >  
					<input type="button" class="btn btn-primary" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
				</div>
			</div>
		</nav>
	</form>
	</div> 
</div>

 
</body>
</html>