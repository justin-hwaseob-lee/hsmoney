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
 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/main.js" charset="utf-8"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HsMoney</title>
 
 
 

</head>




 <body class = "bg-transparent "> 
	<div class="wrap-loading" id="LoadingImage"> 
   	 <div><img src="<%=request.getContextPath()%>/resources/images/sd_loading.gif" width="51" height="50"/> </div>
	</div> 
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}">
	
<div class="card">
  <div class="card-header"> 
    <form method="post" action="updateStartDate.do" style="margin-top:10px;"> 	  
	  <div class="row"> 
		    <div class="col">
		    	<div class="input-group mb-3">
				  <div class="input-group-prepend" >
				    <span class="input-group-text">Start Date</span>
				  </div>
				  <select class="custom-select " id="monthlyStartDate" name="monthlyStartDate"> 
			        <option value="1"  ${startDate eq '1' ? "selected" : ""}>1</option>
			        <option value="2"  ${startDate eq '2' ? "selected" : ""}>2</option>
			        <option value="3"  ${startDate eq '3' ? "selected" : ""}>3</option>
			        <option value="4"  ${startDate eq '4' ? "selected" : ""}>4</option>
			        <option value="5"  ${startDate eq '5' ? "selected" : ""}>5</option>
			        <option value="6"  ${startDate eq '6' ? "selected" : ""}>6</option>
			        <option value="7"  ${startDate eq '7' ? "selected" : ""}>7</option>
			        <option value="8"  ${startDate eq '8' ? "selected" : ""}>8</option>
			        <option value="9"  ${startDate eq '9' ? "selected" : ""}>9</option>
			        <option value="10"  ${startDate eq '10' ? "selected" : ""}>10</option>
			        <option value="11"  ${startDate eq '11' ? "selected" : ""}>11</option>
			        <option value="12"  ${startDate eq '12' ? "selected" : ""}>12</option>
			        <option value="13"  ${startDate eq '13' ? "selected" : ""}>13</option>
			        <option value="14"  ${startDate eq '14' ? "selected" : ""}>14</option>
			        <option value="15"  ${startDate eq '15' ? "selected" : ""}>15</option>
			        <option value="16"  ${startDate eq '16' ? "selected" : ""}>16</option>
			        <option value="17"  ${startDate eq '17' ? "selected" : ""}>17</option>
			        <option value="18"  ${startDate eq '18' ? "selected" : ""}>18</option>
			        <option value="19"  ${startDate eq '19' ? "selected" : ""}>19</option>
			        <option value="20"  ${startDate eq '20' ? "selected" : ""}>20</option>
			        <option value="21" ${startDate eq '21' ? "selected" : ""}>21</option> 
			        <option value="22"  ${startDate eq '22' ? "selected" : ""}>22</option>
			        <option value="23"  ${startDate eq '23' ? "selected" : ""}>23</option>
			        <option value="24"  ${startDate eq '24' ? "selected" : ""}>24</option>
			        <option value="25"  ${startDate eq '25' ? "selected" : ""}>25</option>
			        <option value="26"  ${startDate eq '26' ? "selected" : ""}>26</option>
			        <option value="27"  ${startDate eq '27' ? "selected" : ""}>27</option>
			        <option value="28"  ${startDate eq '28' ? "selected" : ""}>28</option>
			        <option value="29"  ${startDate eq '29' ? "selected" : ""}>29</option>
			        <option value="30"  ${startDate eq '30' ? "selected" : ""}>30</option>
			        <option value="31"  ${startDate eq '31' ? "selected" : ""}>31</option>
			      </select>
			      <div class="input-group-append">
				    <input class="btn btn-primary" type="submit" id="updateStartDate" value="월 시작일 수정">
				  </div>
				</div>
			    
		    </div>
	  </div>
</form>	
  </div>
  
  <div class="card-body">
    <div class="container rounded border border-dark p-3 " style="margin-top:10px; margin-bottom:10px;">

			  <div class="row">
				    <div class="col">
				       <div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <span class="input-group-text">Month Total</span> 
						  </div>
						  <input type="text"    id="monthTotal" value="${monthlyTotal}" readonly class=" form-control" aria-label="Amount (to the nearest dollar)" >
						  <div class="input-group-append">
						    <span class="input-group-text">원</span>
						  </div>
						</div>
				    </div>
			  </div>
	  
	  
	  
	  
			  <div class="row">
				    <div class="col">
				    	<div class="input-group mb-3">
						  <div class="input-group-prepend" > 
						    <span class="input-group-text">Daily Total</span> 
						  </div>
						  <input type="text"  id="dayTotal" value="${dailyTotal}" readonly class="form-control" aria-label="Amount (to the nearest dollar)">
						  <div class="input-group-append">
						    <span class="input-group-text">원</span>
						  </div>
						</div>
				    </div>
			  </div>  
	  
	  

			<form method="post" id="dailyInputForm" action="inputMoney.do"> 	  
				  
				  <div class="row">
					    <div class="col">
					    	<div class="input-group mb-3">
								  <div class="input-group-prepend" > 
								    <span class="input-group-text">Use Date</span> 
								  </div> 
							      <input id="useDate" type="date" name="useDate" class="input-date form-control"/>      
							</div>
					    </div>
				  </div>  
				  
				  
				  <div class="row"> 
					    <div class="col">
					    	<div class="input-group mb-3">
							  <div class="input-group-prepend" >
							    	<select class="custom-select " id="categorySelect" name="categorySelect"> 
								        <option value="음식">음식</option>
								        <option value="커피">커피</option>
								        <option value="쇼핑">쇼핑</option>
								        <option value="여행">여행</option>
								        <option value="세금">세금</option>
								        <option value="주유">주유</option>
								        <option value="기타" selected >기타</option>
								      </select> 
							  </div>
							  
							  <input type="number" id="inputMoney" name="inputMoney"  class="form-control" aria-label="Amount (to the nearest dollar)">
							  <div class="input-group-append">
							    <span class="input-group-text">원</span>
							  </div>
							</div>
							
							
							 
							
					    </div> 
				  </div>
				  
				  <div class="row">
					    <div class="col" >
					      <input class="btn btn-primary" type="button" value="초기화" onclick="resetFunc();">
					      <input class="btn btn-primary" type="submit" id="enter_price" value="입력">
					    </div>
				  </div> 
			</form>	   
	</div>
  </div>
  
</div>
  
 




<nav class="navbar fixed-bottom sticky " style="background-color:#FAFCFC;">
     	Copyright © 2018 HsCompany. All Rights Reserved.
</nav>

</body>
</html>