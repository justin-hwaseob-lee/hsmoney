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
  