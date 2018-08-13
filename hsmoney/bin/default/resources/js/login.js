/**
 * Login Javascript
 */
  
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */  
	$.backstretch( returnUrl(), {speed: 400});	
    //$.backstretch("http://www.nicklitten.com/sites/default/files/blog/3038721-poster-p-2-ibm.jpg", {speed: 400});
    //$('#backstretch').addClass("dim");
	//블러가 안됨...
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
});


function returnUrl() {
	return get("./resources/images/wallpaper-blur-7.jpg");
}

function get(imgSource) {
    var img = new Image();
    img.src = imgSource;
    return img.src;
}