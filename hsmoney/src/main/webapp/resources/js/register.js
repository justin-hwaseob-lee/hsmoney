window.onload = function() {
	/* 메시지가 있을경우 출력부분 */  
	$('#LoadingImage').hide(); 
	var message = $('#message').val(); 
	 if (message != "") {
		 if(message=="존재하는 이름입니다."){
		 swal({
			  title: message, 
			  icon: "warning",  
			  timer:1750,
			  dangerMode: true
			});
		 }
		 else{
			 swal({
				  title: message, 
				  icon: "success",  
				  timer:1750 
				});
		 }
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