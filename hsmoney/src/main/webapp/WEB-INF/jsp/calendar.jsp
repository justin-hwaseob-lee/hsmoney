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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"  crossorigin="anonymous">
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/jquery.number.js" > </script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sweetalert.min.js"></script>	

<!-- chart js -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!-- calendar js -->
<link rel='stylesheet' href='${pageContext.request.contextPath}/resources/css/fullcalendar.css' />
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/jquery.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/moment.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/fullcalendar.js'></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.mobile-events.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar.js" charset="utf-8"></script>  

<style> 
html, body{  
  height:100%;
}  
.highlight {
    background-color: #708EA8;
} 

 .fc h2 {
   font-size: 22px;
   margin-top:15px;
 }
  .fc-right {
   margin-top:10px;
 }
.fc-event-container {
	font-size: 11px;
	text-align:center;
}
table tr:hover td { border-top-color: #708EA8; border-bottom: 1px solid #708EA8; }  
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HsMoney</title>
</head>



 <body class = "bg-transparent "  id="mainbody" name="mainbody"> 
	<div class="wrap-loading" id="LoadingImage"> 
   	 <div><img src="<%=request.getContextPath()%>/resources/images/sd_loading.gif" width="51" height="50"/> </div>
	</div> 
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}"> 
	

<!-- calendar -->
<div id='calendar'  ></div>
	
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
	
	
	
	 
<!-- monthly 테이블 -->
<div class="container-fluid" id="monthlyclass" > 
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




		<nav class="navbar fixed-bottom sticky" id="navbottom" name="navbottom" style="background-color:#FAFCFC;">
		<hr class="divider" style="margin-top:0px; margin-bottom:15px;"></hr>
		 
		 	<div class="input-group mb-3"  style="padding-top: 10px;">
				  <div class="input-group-prepend">
					    <input type="text" value ="Monthly Sum" class="input-group-text30"  style="width:139px; text-align:left;" readonly> 
				  </div>
				  <input type="text" readonly class="form-control input-group-text" id="monthTotal" name="monthTotal" value="${monthlyTotal}" style="width:100px">
				  <div class="input-group-append">
				    <span class="input-group-text" style="width:38px; text-align:left;">원</span>
				  </div>
			</div>
			<div class="input-group mb-3 " style="width:100%;">
					<input id="month_standard" name="month_standard" class="input-group-text30"  type="month" style="width:139px; text-align:left;" onchange="monthChange()">
				 	<input type="text" class="form-control"   id="search" placeholder="Fast Search" name="search" onkeydown="if(event.keyCode == 13) return false;"> 
					<div class=" col-md- text-right" >  
						<input type="button" class="btn btn-primary" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
					</div> 
					
				</div>
				<!-- 
			 <form  id="resultForm2" name="resultForm2"  class="resultForm2 "> 
				<div class="input-group mb-3 " style="width:100%;">
					<input id="month_standard" name="month_standard" class="input-group-text30"  type="month" style="width:139px; text-align:left;" onchange="monthChange()">
				 	<input type="text" class="form-control"   id="search" placeholder="Fast Search" name="search" onkeydown="if(event.keyCode == 13) return false;"> 
					<div class=" col-md- text-right" >  
						<input type="button" class="btn btn-primary" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
					</div> 
					
				</div>
			</form>
			 -->
		</nav>
 
</body> 
</html>