<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




<head>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!-- loading css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loading.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/survey_question.js" charset="utf-8"></script>

<style>
body {
	padding-top: 150px;
	margin: 0
} 
.highlight {
    background-color: #708EA8;
} 
table tr:hover td { border-top-color: #708EA8; border-bottom: 1px solid #708EA8; }  
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HsMoney</title>
</head>




<body>
<!-- 삭제예정 
	<div class="wrap-loading" id="LoadingImage"> 
    <div><img src="<%=request.getContextPath()%>/resources/images/sd_loading.gif" width="51" height="50"/> </div>
	</div>
 -->
	<!-- 'undefined' 메세지를 숨겨줘 -->
	<input type="hidden" id="message" value="${message}">
	

<!-- 상단 제목 및 업로드 버튼, 모달 창 -->
<div class="container-fluid">
	<div class="row form-inline">
	
	
		<div class="form-group col-xs-12 col-md-6">
			<h3 align="left">Survey Target<br/></h3>
			<br/>
		</div>
		
		
		<div class="form-group col-xs-12 col-md-6 text-right">
			<div class="form-group" style="vertical-align:center; ">
			<form method="GET" action="surveyTarget.xlsx" style="margin:0px;"> 
				<input type="submit" class="btn btn-default  pull-right" 
						   style="margin-top: 20px; margin-bottom: 10px; color: #337ab7; background-color: #fff; border-color: #337ab7;" value="Survey Target Form Download">
			</form>
			</div>
			
			<div class="form-group">
				<button type="button" class="btn btn-primary pull-right" style="margin-top: 20px; margin-bottom: 10px;" 
					data-toggle="modal" data-target="#uploadModal">
			  		Survey Target Upload
				</button>
				<!-- 업로드 버튼 클릭시 뜨는 모달창 -->
				<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" style="z-index:1300;">
					<div class="modal-dialog text-left" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="uploadModalLabel">Survey Target Upload</h4>
							</div>
							<div class="modal-body">
								<div class="container-fluid" style="padding-top: 30px; padding-bottom: 30px;">
									<div class="col-xs-12 col-md-9">
										<h4>Survey Target File(.xlsx) Upload:</h4>
									</div>
									<div class="col-xs-12 col-md-3">
										
									</div>
									<br/><br/><br/>
									<form method="POST" action="SurveyTargetUploadFile"	enctype="multipart/form-data">
									<div class="col-xs-10 col-offset-2" >
										<input type="file" class="filestyle" data-buttonBefore="true" data-buttonText="Find file" name="surveyTargetFile" id="surveyTargetFile">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Upload</button>
								</form>
							</div>
						</div>
				  	</div>
				</div> 
			</div>
		</div>
			
			 
			
	</div>
</div>

<!-- 설문 대상 명단 테이블 -->
<div class="container-fluid">
	<div class="table-responsive">
		<table class="table table-striped" style="font-size:12px;"> 
			<thead style="font-size:14px;">
				<tr>
					<th scope="col">SERIAL NO</th>
					<th scope="col">GENDER</th>
					<th scope="col">HIRE TYPE</th>
					<th scope="col">ERBP</th>
					<th scope="col">Onboarding Date</th>
					<th scope="col">HM NAME</th>
					<th scope="col">HM BAND</th>
					<th scope="col">HM EMAIL</th>
					<th scope="col">NH NAME</th>
					<th scope="col">NH BAND</th>
					<th scope="col">NH EMAIL</th>
					<th scope="col">RECT NAME</th>
					<th scope="col">RECT BAND</th>
					<th scope="col">RECT EMAIL</th>
				</tr>
			</thead>
			<tbody id="surveyTargetBody" align="center"> 
			</tbody>  
		</table> 
	</div>
</div>

<nav class="navbar navbar-fixed-bottom" style="background-color:#FAFCFC; padding-bottom:15px;">
<hr class="divider" style="margin-top:0px; margin-bottom:15px;"></hr>
	<div class="form-inline" style="padding-left: 20px; padding-right: 20px;" >
		<div class="form-group col-xs-12 col-sm-6">
			<label for="fastSearch">Fast Search</label>
			<input type="text" class="form-control" id="search" name="search" onkeydown="if(event.keyCode == 13) return false;">
		</div>
		<div class="form-group col-xs-12 col-sm-6 text-right"> 
			<input class="btn btn-primary" type="button" id="deleteSurveyResult" value="Delete" onclick="confirmDelete()">
		</div>
	</div>
</nav>
 
</body>
</html>