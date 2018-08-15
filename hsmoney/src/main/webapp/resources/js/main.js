window.onload = function() {
	$('#LoadingImage').hide(); 
	fnOnload();
}
/*******************************************************************************
 * Form Onload
 ******************************************************************************/
function fnOnload() {
	
	/* 메시지가 있을경우 출력부분 */  
	var message = $('#message').val(); 
	 if (message != "") {
		alert(message);
	}  
	 
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'); 
	var today = new Date();
	var year = today.getFullYear(); 
	var month = today.getMonth(); 
	var day = today.getDate(); 
	var dayName = week[today.getDay()]; 
	document.getElementById('useDate').valueAsDate = new Date(Date.UTC(year,month,day)); 
	
	
	mainPageLoad();
}

/*******************************************************************************
 * 메인페이지 Onload
 ******************************************************************************/
function mainPageLoad(){
	$('#LoadingImage').show(); // loadingImage show
	
	$.ajax({
		type : "get",
		url : "getmainpage.do",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		async : false,
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) {  
		fnPrintGrid(result);  
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		alert("세션이 만료되었습니다.");
		// loading image disappeard
		$('#LoadingImage').hide();
	}
	
}



/*******************************************************************************
 * Result print param : result 결과 Object
 ******************************************************************************/
function fnPrintGrid(result) { 
	if (result.message != null){
		alert(result.message);}
	
	$('#monthlyStartDate').val(result.startDate);
	$('#monthTotal').val(result.monthlyTotal);
	$('#dayTotal').val(result.dailyTotal);
}


function updateStartDate(){
	$('#LoadingImage').show(); // loadingImage show

	var objJson = JSON.stringify(objToJson($(".monthstartTable")
			.serializeArray()));
	$.ajax({
		type : "post",
		url : "updateStartDate.do",
		dataType : "json", 
		data : objJson, 
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) { 
		fnOnload();
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		alert("Error");
		// loading image disappeard
		$('#LoadingImage').hide();
	}
	
}

function inputMainInfoClick(){ 
	$('#LoadingImage').show(); // loadingImage show

	var objJson = JSON.stringify(objToJson($(".inputMoneyForm")
			.serializeArray()));
	$.ajax({
		type : "post",
		url : "inputMoney.do",
		dataType : "json", 
		async : false,
		data : objJson,
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) { 
		fnOnload();
		$('#inputMoney').val('');
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		alert("Error");
		// loading image disappeard
		$('#LoadingImage').hide();
	}
}


/*숫자 3글자마다 콤마*/
$(function(){
	// Set up the number formatting.

	$('#dayTotal').number( true );
	$('#monthTotal').number( true ); 
}); 

function resetFunc() {

	fnOnload();
	$('#categorySelect').val('기타');
	$('#inputMoney').val('');
}
  

/*******************************************************************************
 * form to Json convert
 ******************************************************************************/
function objToJson(formData) {
	var data = formData;
	var obj = {};

	$.each(data, function(idx, ele) {
		obj[ele.name] = ele.value;
	});
	return obj;
}