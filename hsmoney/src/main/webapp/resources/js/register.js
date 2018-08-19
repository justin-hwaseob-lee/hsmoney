window.onload = function() {
	/* 메시지가 있을경우 출력부분 */  
	$('#LoadingImage').hide(); 
	var message = $('#message').val(); 
	 if (message != "") {
		alert(message);
	}   

		
		//모바일 화면에서 왼쪽, 오른쪽 스와이프
		$('#mainbody').swipeleft(function(e, touch) {  //오른쪽이동페이지
			$('#LoadingImage').show(); // loadingImage show
			window.location.href = "main.do"; 
		});
		$('#mainbody').swiperight(function(e, touch) { //왼쪽이동페이지
			$('#LoadingImage').show(); // loadingImage show
			window.location.href = "annually.do"; 
		});
} 