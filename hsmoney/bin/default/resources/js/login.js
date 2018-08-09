window.onload = function() {
	$('#LoadingImage').hide();
	fnOnload();
}
/*******************************************************************************
 * Form Onload
 ******************************************************************************/
function fnOnload() {

	/* 메시지가 있을경우 출력부분 */ 
	/*
	var message = $('#message').val();
	if (message != "") {
		alert(message);
	}
 */ 
 
	/* 전체 소비 결과 조회 */ 
	fnMoneyResultSearch();
}


/*******************************************************************************
 * 전체 설문조사 결과 조회 Ajax 처리
 ******************************************************************************/
function fnMoneyResultSearch() {
	$('#LoadingImage').show(); // loadingImage show
	$.ajax({
		type : "get",
		url : "moneyResult.do",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) { 
		fnPrintGrid(result);
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
			var use_date = result.moneyList[i].use_date;
			var category = result.moneyList[i].category; 
			var price = result.moneyList[i].price; 
			  

			appendData += "<tr class='line'>";
			appendData += "<td align='center' class='text-center' style='vertical-align: middle'><input type='checkbox' onclick='chkclickevent(this);' name='chk' id='chk' value='"
					+ money_id + "'></td>";  
			appendData += "<td class='text-center' style='vertical-align: middle'>" + use_date +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + category +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + price +"</td>"; 
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
	table.find('tr').each(function(index, row) {
		var allCells = $(row).find('td');
		if (allCells.length > 0) {
			var found = false;
			allCells.each(function(index, td) {
				var regExp = new RegExp(inputVal, 'i');
				if (regExp.test($(td).text())) {
					found = true;
					return false;
				}
			});
			if (found == true)
				$(row).show();
			else
				$(row).hide();
		}
	});
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
   