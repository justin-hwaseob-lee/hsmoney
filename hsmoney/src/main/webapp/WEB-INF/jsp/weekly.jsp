<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 

<head>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/ico/hsmoney_ico.png" />
<c:import url="header.jsp" />

 
<!-- loading css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loading.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
<!-- jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!--  jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 

<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery.number.js" > </script> --%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.mobile-events.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sweetalert.min.js"></script>

<!-- chart js -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/weekly.js" charset="utf-8"></script>  
 
 
<style>     
.ui-datepicker-trigger { position:relative; height:38px;width:38px; } 
 
html, body{ 
  height:100%;
} 
.highlight {
    background-color: #708EA8;
} 
table tr:hover td { border-top-color: #708EA8; border-bottom: 1px solid #708EA8; }  
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HsMoney</title>
</head>

 
 <body  id="mainbody" name="mainbody">  
 
 
	<div class="wrap-loading" id="LoadingImage"> 
   	 <div><img src="<%=request.getContextPath()%>/resources/images/sd_loading.gif" width="51" height="50"/> </div>
	</div> 
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}">
 <input type="hidden" id="startDate" value="${startDate}">
 <input type="hidden" id="endDate" value="${endDate}">
 
 
	
<!-- chart -->	
<div class="accordion" id="accordionExample">
		  <div class="card" style="background-color:#FAFCFC;">
		    <div class="card-header col-md- text-right" id="headingOne">
		      <h5 class="mb-0">
		        <button class="btn btn-primary text-right" type="button" data-toggle="collapse" data-target="#collapseOnes" aria-expanded="true" aria-controls="collapseOnes" onclick="checkStatistic()">통계보기</button>
		      </h5>
		    </div>
		
		    <!-- <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample"> -->
		    <!-- <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample"> -->
		    <div id="collapseOne" class="collapse">
		    	<center>
				      <div class="card-body"> 
				       		<div id="donutchart"></div>
				      </div>
			      </center>
		    </div>
		  </div>
  </div> 
<!-- chart -->	
	
 
<!-- weekly 테이블 -->
<div class="container-fluid" id="monthlyclass"  style="margin-bottom:169px;"> 
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

	</form>
	
	
	</div> 
</div>

		<nav class="navbar fixed-bottom sticky" id="navbottom" name="navbottom"  style="background-color:#FAFCFC;">
		<hr class="divider" style="margin-top:0px; margin-bottom:15px;"></hr>
		 
		 
		 
		 
			 
			 <div class="input-group mb-3"  style="padding-top: 10px;">
				  <div class="input-group-prepend">
					    <input type="text" value ="Week Sel" class="input-group-text30" style="width:110px; text-align:center;" readonly> 
				  </div>
				  <input type='text' id="week-picker" name="week-picker" class="form-control input-group-text" placeholder="Select Week" disabled readonly />
			</div>
             
             
		 	<div class="input-group mb-3" >
				  <div class="input-group-prepend">
					    <input type="text" value ="Week Sum" class="input-group-text30" style="width:110px;text-align:left;"  readonly> 
				  </div>
				  <input type="text" readonly diabled class="form-control input-group-text" id="weekTotal" name="weekTotal" value="${weeklyTotal}" style="width:100px">
				  <div class="input-group-append">
				    <span class="input-group-text" style="width:38px; text-align:left;" >원</span>
				  </div>
			</div>
			 
			 
			<div class="input-group mb-3 " >
				<input type="text" class="form-control" id="search" placeholder="Fast Search" name="search" onkeydown="if(event.keyCode == 13) return false;">
				
				<div class="col-md- text-right" >  
					<input type="button" class="btn btn-primary" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
				</div>
			</div>
			 
			 
			 
		</nav> 
</body> 
</html>