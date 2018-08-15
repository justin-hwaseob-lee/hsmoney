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
}