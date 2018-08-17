window.onload = function() {
	$('#LoadingImage').hide();
	fnOnload();

	//datepicker위치 강ㅈ
	$.extend($.datepicker, {
	    _checkOffset: function(inst, offset, isFixed) {
	        offset.top = $("#" + inst.id).offset().top - $(window).scrollTop() + $("#" + inst.id)[0].getBoundingClientRect().height-305;
	        offset.left = $("#" + inst.id).offset().left   + $("#" + inst.id)[0].getBoundingClientRect().width-260;
	        return offset;
	    }
	});


}

 
/*******************************************************************************
 * datepicker 날짜고르기
 ******************************************************************************/  
$(function() {
    var startDate;
    var endDate;
     
    $('#week-picker').datepicker( {
		buttonImage : "resources/images/calendar_icon2.png", // 표시할이미지 

		  showOptions: { direction: "down" },
		showOn : "both", // 버튼과 텍스트필드 모두 표시
		buttonImageOnly : true,
        showOtherMonths: true,
        selectOtherMonths: true,
        selectWeek:true,   
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
            var dateFormat = 'yy/mm/dd'
            startDate = $.datepicker.formatDate( dateFormat, startDate, inst.settings );
            endDate = $.datepicker.formatDate( dateFormat, endDate, inst.settings );

            $('#week-picker').val(startDate + '~' + endDate);
            
            //여기에 함수 추가
            weekChange(startDate,endDate);
            
            setTimeout("applyWeeklyHighlight()", 100);
        },
		  beforeShow : function(input, inst) {    
		   setTimeout("applyWeeklyHighlight()", 100);
		  }
    });
});
 
function applyWeeklyHighlight() {
	
	 $('.ui-datepicker-calendar tr').each(function() { 
	  if ($(this).parent().get(0).tagName == 'TBODY') {
	   $(this).mouseover(function() {
	    $(this).find('a').css({
	     'background' : '#ffffcc',
	     'border' : '1px solid #dddddd'
	    });
	    $(this).find('a').removeClass('ui-state-default');
	    $(this).css('background', '#ffffcc');
	   });
	   
	   $(this).mouseout(function() {
	    $(this).css('background', '#ffffff');
	    $(this).find('a').css('background', '');
	    $(this).find('a').addClass('ui-state-default');
	   });
	  }

	 });
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
	/* 주간 소비 결과 조회 */ 
	fnMoneyResultSearch();
}
 



/*******************************************************************************
 * 주간 소비결과 조회 Ajax 처리
 ******************************************************************************/
function fnMoneyResultSearch() {

	/*이번주 자동으로 선택해서 세팅해놓기*/  
	var date = new Date(); 
    startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
    endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
    var dateFormat = 'yy/mm/dd'
    startDate = $.datepicker.formatDate( dateFormat, startDate);
    endDate = $.datepicker.formatDate( dateFormat, endDate );

    $('#week-picker').val(startDate + '~' + endDate); 
    weekChange(startDate,endDate); 
}


/*******************************************************************************
 * Result print param : result 결과 Object
 ******************************************************************************/
function fnPrintGrid(result) {
	var length = result.moneyList.length;
	$("#moneyListBody").empty();
	var appendData = "";

	if (result.message != null)
		alert(result.message);

	// 데이터 조회된 길이를 체크하여 데이터 없는 경우 아래와 같은 메시지를 표에 표시
	// '조회된 데이터가 없습니다.'
	if (parseInt(length) > 0) {

		for (var i = 0; i < length; i++) {
			var money_id = result.moneyList[i].money_id; // pk
			var user_id = result.moneyList[i].user_id;
			var use_date = result.moneyList[i].use_date;
			var category = result.moneyList[i].category; 
			var price = result.moneyList[i].price; 
			var start_date = result.moneyList[i].start_date;
			  

			appendData += "<tr class='line'>";
			appendData += "<td align='center' class='text-center' style='vertical-align: middle'><input type='checkbox' onclick='chkclickevent(this);' name='chk' id='chk' value='"
					+ money_id + "'>"
					+"<input type='hidden' id='start_date' name='start_date' value='"+ start_date + "'>";
					+"<input type='hidden' name='user_id' value='" + user_id + "'></td>";  
			appendData += "<td class='text-center' style='vertical-align: middle'>" + use_date +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + category +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + price + " </td>"; 
			appendData +="</tr>";
		}

		// Table에 조회 데이터 입력
		$("#moneyListBody").append(appendData);

	} else {
		// Paging 처리 초기화(삭제)
		$("#pageNav").empty();
		appendData = "<tr style='background-color:#F9F9F9'>";
		appendData += "<th colspan='11' class='text-center'>";
		appendData += "조회된 데이터가 없습니다.";
		appendData += "</th>";
		appendData += "</tr>";

		// 조회된 데이터 없음 메시지 출력
		$("#moneyListBody").append(appendData);
		
	}
	
 
	//$('#weekTotal').val(result.monthlyTotal); 
}


function confirmDelete() {
 
	var selected=$("input[name='chk']:checked").val();
	if(selected==null){
		alert("선택된 항목이 없습니다.");
		return;
	} 
	
	var del = confirm("정말 삭제 하시겠습니까?");
	if (del == true) {
		fndeleteSurveyResult();
	} else {
		return;
	}
}

 
function weekChange(startDate,endDate){
	/*
	alert(startDate);
	alert(endDate);
	*/

	var tmp={};
	tmp["startDate"]=startDate;
	tmp["endDate"]=endDate;
	var objJson = JSON.stringify(tmp); 
	
	$('#search').val(''); 
	$('#LoadingImage').show(); // loadingImage show
	$.ajax({
		type : "post",
		url : "weekChange.do",
		dataType : "json",
		data : objJson,
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) {
		//여기
		fnPrintGrid(result);
		getSum(); //테이블에 보이는 price값 다 더하기
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		alert("세션이 만료되었습니다.");

		window.location.href = "main"; 
		// loading image disappeard
		$('#LoadingImage').hide();
	}
}

/*숫자 3글자마다 콤마*/
$(function(){
	// Set up the number formatting.  
	$('#weekTotal').number( true );  
}); 


/*******************************************************************************
 * 선택항목 삭제
 ******************************************************************************/
function fndeleteSurveyResult() {
	var tmp = {}; 
	tmp["chk"] = $("input[name='chk']:checked").map(function() {
		return this.value;
	}).get();

	var objJson = JSON.stringify(tmp);

	$('#LoadingImage').show(); // loadingImage show
	$.ajax({
		type : "post",
		url : "deleteSelected.do",
		data : objJson,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) {
		fnPrintGrid(result);
		getSum(); //테이블에 보이는 price값 다 더하기
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		alert("Error");

		// loading image disappeard
		$('#LoadingImage').hide();
	}
}






/*******************************************************************************
 * 테이블 4 컬럼 price값 다 더하기
 ******************************************************************************/
function getSum(){
	var table= $('#moneyListBody');
	var realtimesum=0;
	table.find('tr').each(function(index, row) {
		var allCells = $(row).find('td'); 
		if (allCells.length > 0) {
			var res1=$(row).find('td:nth-child(4)').text();  //가격란 값들 다 가져와서
			realtimesum=realtimesum +parseInt(res1,10); //실시간검색결과에 추가
		}
	});
	$('#weekTotal').val(realtimesum);
}




/*******************************************************************************
 * 실시간 검색결과 추출
 ******************************************************************************/
$(document).ready(function() {
	$('#search').keyup(function() {
		searchTable($(this).val());
	});
});

function searchTable(inputVal) {
	var table = $('#moneyListBody');
	var realtimesum=0;
	table.find('tr').each(function(index, row) {
		var allCells = $(row).find('td');
		
		if (allCells.length > 0) {
			var found = false;
			allCells.each(function(index, td) {
				if(index==3) return false; //가격은 검색하지 않음
				var regExp = new RegExp(inputVal, 'i');
				if (regExp.test($(td).text())) {
					found = true;
					return false;
				}
			});
			
			if (found == true){
				$(row).show();
				var res1=$(row).find('td:nth-child(4)').text();  //가격란 값들 다 가져와서
				realtimesum=realtimesum +parseInt(res1,10); //실시간검색결과에 추가
			}
			else
				$(row).hide();
		}
	});
	$('#weekTotal').val(realtimesum);
}

/* 전체선택 */ 
$(document).ready(function() {
	// 최상단 체크박스 클릭
	$("#checkall").click(function() {
		var fastSearch=$('#search').val();
		
		if(fastSearch==""){
			// 클릭되었으면
			if ($("#checkall").prop("checked")) {
				// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
				$("input[name=chk]").prop("checked", true);  
				checkAllLine();
				// 클릭이 안되있으면
			} else {
				// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
				$("input[name=chk]").prop("checked", false); 
				checkAllLineBack();
			}
		}
		else{
			alert("When you use fast search, you can not use all select\n(please select individually)");
			//$("input[name=checkall]").prop("checked", false);
			$("#checkall").prop("checked", false);
		}
	});
});

function checkAllLine(){
	$(".line").css('background', '#C9D7E4');
	/*$(".line").css('color', '#000080');*/
}

function checkAllLineBack(){
	$(".line").css('background', 'none');
	$(".line").css('color', 'black');
}
 

/*******************************************************************************
 *  해당 row 선택시 색깔 변하도록 하기
 ******************************************************************************/ 
$('body').on('click', '#moneyTable tbody  tr', function (e) {
    if (e.target.type == "checkbox") {
        // stop the bubbling to prevent firing the row's click event  
        e.stopPropagation(); 
    } else {
        var $checkbox = $(this).find(':checkbox');
        $checkbox.attr('checked', !$checkbox.attr('checked'));

        if($checkbox.attr('checked')){
			$(this).css('background', '#C9D7E4'/*'#D9D6FF'*/);
			/*$(this).css('color', '#000080');*/
        }
        else{
			$(this).css('background', 'none');
			$(this).css('color', 'black');
        	
        }
    }
});

/*******************************************************************************
 * 해당 row대신 체크박스 선택시 색깔 변하도록 하기
 ******************************************************************************/ 
function chkclickevent(chkbox) {    
	if (chkbox.checked)
	{
		$(chkbox).parent().parent().css("background","#C9D7E4"); 
		/*$(chkbox).parent().parent().css("color","#000080");   */
	}
	else{
		$(chkbox).parent().parent().css("background","none"); 
		$(chkbox).parent().parent().css("color","black");   
	}
}; 
 

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