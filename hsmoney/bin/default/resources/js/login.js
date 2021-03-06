window.onload = function() {
	$('#LoadingImage').hide(); 
	fnOnload();
}

function autologinchanged() { 
		if ($('#autologin').prop('checked')){ //autologin이 체크되면 remember도 체크 
        	$('#remember').prop("checked", true);  
	    } 
}


/*******************************************************************************
 * Form Onload
 ******************************************************************************/
function fnOnload() {
	
	
	
	//logout한경우 지워주기
	var logout = $('#logout').val();
	if(logout=='true'){  

		var autologin = Cookies.get("autologin");
		if(autologin=='true'){
	    	Cookies.remove('autologin');  
		}  
    	logout='false'; 
    	$('#logout').val(''); 
	} 
	
	/* 메시지가 있을경우 출력부분 */  
	var message = $('#message').val(); 
	 if (message != "") { 
		 swal({
			  title: message, 
			  icon: "warning",  
			  timer:1750,
			  dangerMode: true
			});
	}   
	 var remember = Cookies.get("remember");
	 var username = Cookies.get("username");
	 var password = Cookies.get("password"); 
	 var autologin = Cookies.get("autologin"); 
        
        if (remember == 'true')  { 
            // autofill the fields 
        	$('#form-username').val(username);
        	$('#form-password').val(password);
        	$('#remember').prop("checked", true); 
        	if(autologin=='true'){
        		$('#autologin').prop("checked", true);
        		
        		//자동로그인 설정했을시
       		 	submitForm();
       		 	post_to_url("main.do","post")
        		window.location.replace('main');
        	}
        	else
        		$('#autologin').prop("checked", false);
        }
        else{
        	$('#form-username').val('');
        	$('#form-password').val('');
        	$('#remember').prop("checked", false);  
        	$('#autologin').prop("checked", false);   
        }
}

function post_to_url(path, method) {
    method = method || "post"; // 전송 방식 기본값을 POST로
 
    
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
 
    //히든으로 값을 주입시킨다. 
 
    document.body.appendChild(form);
    form.submit();
}
/*
function post_to_url(path, params, method) {
    method = method || "post"; // 전송 방식 기본값을 POST로
 
    
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
 
    //히든으로 값을 주입시킨다.
    for(var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);
 
        form.appendChild(hiddenField);
    }
 
    document.body.appendChild(form);
    form.submit();
}
*/


function submitForm() {  
	//cookisetting(); 
	$('#LoadingImage').show(); // loadingImage show
 
	var objJson = JSON.stringify(objToJson($(".loginForm")
			.serializeArray())); 
	
	
	$.ajax({
		type : "post",
		url : "login.do",
		dataType : "json",
		data : objJson,
		async : false, 
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) { 
		if (result.message != null){
			//로그인 실패한경우 
	    	Cookies.remove('autologin'); 
			$('#message').val(result.message); 
		}
		else{//성공한 경우에만 설정저장 
			cookisetting();    
		}  
		// loading image disappeard
		$('#LoadingImage').hide();  
	}

	function whenError(result) { 
		swal({
			  title: "세션이 만료되었습니다.", 
			  icon: "warning",  
			  timer:1750,
			  dangerMode: true
			});
		//aler("세션이 만료되었습니다.");
		//alert("Error");  
		// loading image disappeard
		$('#LoadingImage').hide();
	}
	  
}  
 
function cookisetting(){
	if ($('#remember').prop('checked')) //remember 체크박스가 체크되어 있으면 쿠키에 저장
    {
        var username = $('#form-username').val();
        var password = $('#form-password').val();
   	    var autologin =  $('#autologin').val(); 

        // set cookies to expire in 31 days 
		 Cookies.set('username', username, { expires: 31 });
		 Cookies.set('password', password, { expires: 31 });
		 Cookies.set('remember', true, { expires: 31 });       
		 
		 if ($('#autologin').prop('checked'))
			 Cookies.set('autologin', true, { expires: 31 });  
		 else
			 Cookies.set('autologin', false, { expires: 31 });   
    }
    else //체크되어있지 않으면 지우기
    {
        // reset cookies
    	Cookies.remove('username');
    	Cookies.remove('password');
    	Cookies.remove('remember');
    	Cookies.remove('autologin'); 
    }  
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