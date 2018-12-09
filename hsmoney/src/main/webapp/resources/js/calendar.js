window.onload = function() {
	$('#LoadingImage').hide();
	var mainResult;
	fnOnload();
	

	//모바일 화면에서 왼쪽, 오른쪽 스와이프
	$('#mainbody').swipeleft(function(e, touch) {  //오른쪽이동페이지
		$('#LoadingImage').show(); // loadingImage show
		window.location.href = "weekly.do"; 
	});
	$('#mainbody').swiperight(function(e, touch) { //왼쪽이동페이지
		$('#LoadingImage').show(); // loadingImage show
		window.location.href = "main.do"; 
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
 
 
	var today = new Date();
	var year = today.getFullYear(); 
	var month = today.getMonth();   
	document.getElementById('month_standard').valueAsDate = new Date(Date.UTC(year,month)); 
	
	
	/* 월 소비 결과 조회 */ 
	monthChange(); 
}

 
/*******************************************************************************
 * Result print param : result 결과 Object
 ******************************************************************************/
function fnPrintGrid(result) {
	//mainResult=result;
	mainResult=result.moneyList.filter(use_date => !(   new Date(startDate) <= new Date(use_date)  &&  new Date(use_date) <= new Date(endDate) ) )
	var length = result.moneyList.length;
	$("#moneyListBody").empty();
	var appendData = "";

	if (result.message != null){
		//alert(result.message);

		if(result.message=="성공적으로 삭제되었습니다."){
			 swal({
				  title: result.message, 
				  icon: "success",  
				  timer:1750 
				}); 
		}
		else{
			swal({
				  title: result.message, 
				  icon: "warning",  
				  timer:1750,
				  dangerMode: true
				});
		}
	}

	// 데이터 조회된 길이를 체크하여 데이터 없는 경우 아래와 같은 메시지를 표에 표시
	// '조회된 데이터가 없습니다.'
	var startDate=result.startDate;
	var endDate=result.endDate;
	 
	if (parseInt(length) > 0) {

		for (var i = 0; i < length; i++) {
			var money_id = result.moneyList[i].money_id; // pk
			var user_id = result.moneyList[i].user_id;
			var use_date = result.moneyList[i].use_date;
			var category = result.moneyList[i].category; 
			var price = result.moneyList[i].price; 
			var start_date = result.moneyList[i].start_date; 
			
			if(!(   new Date(startDate) <= new Date(use_date)  &&  new Date(use_date) <= new Date(endDate) ))
				continue;
			/*
			if(!(startDate<=use_date && use_date<=endDate)){
				//alert("실제 검색 결과 "+startDate+" / "+endDate+ " : "+use_date);
				continue;
			}*/
			appendData += "<tr class='line'>";
			appendData += "<td align='center' class='text-center' style='vertical-align: middle'><input type='checkbox' onclick='chkclickevent(this);' name='chk' id='chk' value='"
					+ money_id + "'>"
					+"<input type='hidden' id='start_date' name='start_date' value='"+ start_date + "'>";
					+"<input type='hidden' name='user_id' value='" + user_id + "'></td>";  
			appendData += "<td class='text-center' style='vertical-align: middle'>" + use_date +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + category +"</td>";
			appendData += "<td class='text-center' style='vertical-align: middle'>" + addCommas(price) + " </td>"; 
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

	$("table tbody tr:odd").addClass("odd");
	$("table tbody tr:even").addClass("even");
 
	//$('#monthTotal').val(result.monthlyTotal); 
	
	

	var navHeight = $("#navbottom").outerHeight(); 
	document.getElementById('monthlyclass').style.marginBottom=navHeight+"px";

	myCalendar(result);
}


function confirmDelete() {
 
	var selected=$("input[name='chk']:checked").val();
	if(selected==null){
		swal({
			  title: "선택된 항목이 없습니다.",
			  icon: "warning",  
			  timer:1750,
			  dangerMode: true
			  
			});
		//alert("선택된 항목이 없습니다.");
		return;
	} 

	
	swal({
	  title: "정말 삭제 하시겠습니까?",
	  text: "삭제 후에는 복구되지 않습니다!",
	  icon: "warning",
	  buttons: true,
	  dangerMode: true,
	})
	.then((willDelete) => {
	  if (willDelete) {
		  fndeleteSurveyResult(); 
	  } else { 
	  }
	});
	/*
	var del = confirm("정말 삭제 하시겠습니까?");
	if (del == true) {
		fndeleteSurveyResult();
	} else {
		return;
	}
	*/
}

function monthChange(){
	$('#search').val(''); 
	$('#LoadingImage').show(); // loadingImage show

	var tmp = {};
	tmp["month_standard"] = $('#month_standard').val();  
	var objJson = JSON.stringify(tmp);
	$.ajax({
		type : "post",
		url : "calendar.do",
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

		refreshCheckStatistic();
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
		window.location.href = "main";
		//alert("Error");

		// loading image disappeard
		$('#LoadingImage').hide();
	}
}

/*숫자 3글자마다 콤마
$(function(){
	// Set up the number formatting.  
	$('#monthTotal').number( true );  
}); 
*/

/*******************************************************************************
 * 선택항목 삭제
 ******************************************************************************/
function fndeleteSurveyResult() {
	var tmp = {}; 
	tmp["month_standard"] = $('#month_standard').val(); 
	tmp["chk"] = $("input[name='chk']:checked").map(function() {
		return this.value;
	}).get();
	
	var objJson = JSON.stringify(tmp);

	$('#LoadingImage').show(); // loadingImage show
	$.ajax({
		type : "post",
		url : "deleteMonthSelected.do",
		data : objJson,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : whenSuccess,
		error : whenError
	});

	function whenSuccess(result) {
		fnPrintGrid(result);
		refreshCheckStatistic();
		getSum(); //테이블에 보이는 price값 다 더하기
		// loading image disappeard
		$('#LoadingImage').hide();
	}

	function whenError(result) {
		//alert("Error");

		swal({
			  title: "세션이 만료되었습니다.", 
			  icon: "warning",  
			  timer:1750,
			  dangerMode: true
			});

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
			//var res1=$(row).find('td:nth-child(4)').text();  //가격란 값들 다 가져와서
			//realtimesum=realtimesum +parseInt(res1,10); //실시간검색결과에 추가
			var res1=parseInt($(row).find('td:nth-child(4)').text().replace(/,/g, ''));  //가격란 값들 다 가져와서
			realtimesum=realtimesum+res1;
		}
	});
	$('#monthTotal').val(addCommas(realtimesum));
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
				//var res1=$(row).find('td:nth-child(4)').text();  //가격란 값들 다 가져와서
				//realtimesum=realtimesum +parseInt(res1,10); //실시간검색결과에 추가
				var res1=parseInt($(row).find('td:nth-child(4)').text().replace(/,/g, ''));  //가격란 값들 다 가져와서
				realtimesum=realtimesum+res1;
			}
			else
				$(row).hide();
		}
	});
	$('#monthTotal').val(addCommas(realtimesum));
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
			swal({
				  title: "검색중에는 사용불가합니다.", 
				  icon: "warning",  
				  timer:1750,
				  dangerMode: true
				}); 
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
	$("tr:odd").css("background-color", 'rgba(0,0,0,0.05)');
	$("tr:even").css("background-color", '#FFFFFF');
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
			$(this).css('background', '#C9D7E4'); 
        }
        else{

        	if( $(e.target.parentElement).attr('class').indexOf('even') > -1) {
        		$(this).css('background-color', 'rgba(0, 0, 0, 0.05) ');
        	}
        	else{
        		$(this).css("background-color", '#FFFFFF'); 
        	} 
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
 * calendar
 ******************************************************************************/
function myCalendar(result){ 
	commonwork();
    $('#calendar').fullCalendar('destroy'); 
	$('#calendar').fullCalendar({
	    // put your options and callbacks here
        defaultDate : $('#month_standard').val()+"-2", 
        defaultView: 'month',
        showNonCurrentDates :false, //이번달 말고는 블록처리 안보이게
        fixedWeekCount :false, //보일필요 없는 날짜는 달력에서 제거(맨마지막줄)
        eventLimit : true, 
        events:calendarList,
        titleFormat:'YYYY년 MM월',
	    monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
	    monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"], 
        height:470,
        eventColor: '#C9D7E4'
	  });

	$('.fc-today-button').click(function(){
		$('#collapseOne').hide(500); //통계보기 열려있다면 닫기
		   var date=new Date();
		   var current = new Date(date.getFullYear(), date.getMonth(), 1);
		   
		   $('#month_standard').val(moment(current).format('YYYY-MM'));
		   monthChange();
		});

	$('.fc-prev-button').click(function(){ 
		$('#collapseOne').hide(500); //통계보기 열려있다면 닫기
		   var date=new Date($('#month_standard').val());
		   var current = new Date(date.getFullYear(), date.getMonth()-1, 1);
		   
		  // $('#month_standard').val(current.getFullYear()+"-"+(current.getMonth()+1));
		   $('#month_standard').val(moment(current).format('YYYY-MM'));
		   monthChange();
		});

	$('.fc-next-button').click(function(){
		$('#collapseOne').hide(500); //통계보기 열려있다면 닫기
	   var date=new Date($('#month_standard').val());
	   var current = new Date(date.getFullYear(), date.getMonth()+1, 1);
	   
	  // $('#month_standard').val(current.getFullYear()+"-"+(current.getMonth()+1));
	   $('#month_standard').val(moment(current).format('YYYY-MM'));
	   monthChange();
	});

	
}


/*******************************************************************************
 * common works
 ******************************************************************************/
function commonwork(){
	/*달력에 들어갈 정보 정보*/
	var result=mainResult;
	var length = result.moneyList.length;
	var dateList=[];
	var categoryList=[];
	if (parseInt(length) > 0) 
		for (var i = 0; i < length; i++) {
			categoryList.push(result.moneyList[i].category);
			dateList.push(result.moneyList[i].use_date);
		}
	
	categoryList=categoryList.filter(onlyUnique)
	dateList=dateList.filter(onlyUnique)
	
	var categorySumList=[];
	var dateSumList=[];
	

	for(var j=0; j<categoryList.length; j++)
		categorySumList.push(0);
	for(var j=0; j<dateList.length; j++)
		dateSumList.push(0);
	
	
	if (parseInt(length) > 0) { 
		var jj=0;
		for (var i = 0; i < length; i++) {
			for(; jj<dateList.length; jj++){
				if(dateList[jj]==result.moneyList[i].use_date){
					dateSumList[jj] = dateSumList[jj] + parseInt(result.moneyList[i].price);
					break;
				}
			}
			for(var j=0; j<categoryList.length; j++){
				if(categoryList[j]==result.moneyList[i].category){
					categorySumList[j] = categorySumList[j] + parseInt(result.moneyList[i].price);
					break;
				}
			}
		}
	} 
	
	//달력에 표시할 데이터
    calendarList = [];
    for(var i=0; i<dateList.length; i++){
    	calendarList.push({title:addCommas(dateSumList[i]), start:dateList[i]});
    }
    

	 //그래프에 표시할 데이터
   graphDataRow = [];
   graphDataRow.push(['Category', 'Amount']);
	
   for(var i = 0; i < categoryList.length; i++) //랜덤 데이터 생성
   	graphDataRow.push([categoryList[i] +" "+ addCommas(categorySumList[i])+"원" , parseInt(categorySumList[i])]); 
}



/*******************************************************************************
 * 통계정보 보기
 ******************************************************************************/
function checkStatistic(){

	$('#collapseOne').toggle(1000); //통계보기 열려있다면 닫기
	refreshCheckStatistic();
}

function refreshCheckStatistic(){
	$('#LoadingImage').show();
	commonwork(); 
	
	//아래는 그리기 
	google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart); 
    function drawChart() { 
      
      var data = google.visualization.arrayToDataTable(graphDataRow); 
      data.sort([{column: 1, desc:true}]);
      var options = {     
          chartArea:{width:'80%', height:'100%'},
          legend: {
            position: 'right' 
          }, 
          fontSize : 12,
          width:380,
          height:220,  
          is3D: true,
      };
      
      var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
      google.visualization.events.addListener(chart, 'ready', afterDraw);
      chart.draw(data, options); 
    }
}
function afterDraw(){ 
	$('#LoadingImage').hide();
}

function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}

/**
 * 검색 조건에 따른 배열 필터링(쿼리)
 */
function filterItems(query) {
  return fruits.filter(function(el) {
      return el.toLowerCase().indexOf(query.toLowerCase()) > -1;
  })
}

function addCommas(nStr){
	 nStr += '';
	 var x = nStr.split('.');
	 var x1 = x[0];
	 var x2 = x.length > 1 ? '.' + x[1] : '';
	 var rgx = /(\d+)(\d{3})/;
	 while (rgx.test(x1)) {
	  x1 = x1.replace(rgx, '$1' + ',' + '$2');
	 }
	 return x1 + x2;
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