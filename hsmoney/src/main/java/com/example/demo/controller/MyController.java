package com.example.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.dto.MainInfoDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MoneyDto;
import com.example.demo.service.MoneyService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 
 

@Controller
public class MyController {

	@Autowired
	private MoneyService moneyService;

	@GetMapping("/")
	public String rootDir(HttpSession session){ 
		System.out.println("main called /"); 
		if (session.getAttribute("loginInfo") != null)  // LoginInfo exists in session
			return "main";
		else
			return "login";
	}
	@GetMapping("main")
	public String rootMain(HttpSession session){ 
		System.out.println("main called /main");
		if (session.getAttribute("loginInfo") != null)  // LoginInfo exists in session
			return "main";
		else
			return "login";
	}
	
	@GetMapping("main.do")
	public String mainPage(HttpSession session){
		System.out.println("\n called main.do get");
		if (session.getAttribute("loginInfo") != null)  // LoginInfo exists in session
			return "main";
		else
			return "login"; 
	}	
	@GetMapping("main2.do")
	public ModelAndView m2ainPage(HttpSession session){
		System.out.println("\n 2called main.do geteee");
		ModelAndView mav=new ModelAndView("main");
		if (session.getAttribute("loginInfo") != null) {  // LoginInfo exists in session
			mav.addObject("message", "금액이 입력되었습니다.");return mav;}
		else {
			mav.setViewName("login");
			return mav;} 
	}
	
	@PostMapping("main.do")
	public ModelAndView mainPagePost(HttpSession session){
		System.out.println("\n called main.do get");
		ModelAndView mav=new ModelAndView();
		if (session.getAttribute("loginInfo") != null) { // LoginInfo exists in session
			mav.setViewName("main"); 
			mav.addObject("firstlogin", "true");
		}
		else {
			mav.addObject("message", "ID/PW를 확인해주세요!");
			mav.setViewName("login");
		}
		return mav;
	}
	@GetMapping("getmainpage.do")
	public ModelAndView doMain(HttpSession session) {  
		System.out.println("\n called getmainpage.do get"); 
		
		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login");
		
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		MainInfoDto mainInfo=null;
		
		int start_date=moneyService.getStartDate(userInfo.getUser_id());
		
		Date startDate = new Date();	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		//2017-8 
		int select_year = calendar.get(Calendar.YEAR);  //select Year   - 2017
		int select_month = calendar.get(Calendar.MONTH); //select month - 7

		String search_start=null;
		String search_end=null;
		if(start_date <= 15) { //시작일이 15일 이전인경우
			search_start = select_year+"-"+(select_month+1)+"-"+start_date; //2017-7-1
			
			if(start_date==1) { //시작일이 1인인경우
				calendar.set(select_year, select_month, 1);
				int dayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				search_end=select_year+"-"+(1+select_month)+"-"+dayofMonth;
			}
			
			else { //2~15
				if(select_month+2 == 13)
					search_end = (1+select_year)+"-1-"+(start_date-1);
				else
						search_end = select_year+"-"+(select_month+2)+"-"+(start_date-1);
			}
		}
		else { //15일이후
			if(select_month==0) //1월선택
				search_start=(select_year-1)+"-12-"+start_date;
			else 
				search_start=select_year+"-"+select_month+"-"+start_date;
			search_end=select_year+"-"+(select_month+1)+"-"+(start_date-1);
		}
		mainInfo=moneyService.getMainInfo(userInfo.getUser_id(), search_start, search_end);
		if(mainInfo!=null) {  
			map.put("startDate", mainInfo.getStart_date());
			map.put("monthlyTotal", mainInfo.getMonthly_use());
			map.put("weeklyTotal", mainInfo.getWeekly_use());
			map.put("dailyTotal", mainInfo.getDaily_use());
		}
		else { 
			map.put("monthlyTotal", "0");
			map.put("dailyTotal", "0");
			map.put("weeklyTotal", "0");
			map.put("startDate", moneyService.getStartDate(userInfo.getUser_id())); 
		} 
		return new ModelAndView("jsonView", map); 
	}
	

	@PostMapping("monthChange.do")
	public ModelAndView doMonthChange(@RequestBody String json, HttpSession session){   

		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login"); 
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		String user_id=userInfo.getUser_id();

		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String month_standard = (String) jsonObject.get("month_standard");   
		List<MoneyDto> moneyList = null;
		 
		int start_date=moneyService.getStartDate(user_id);
		Date startDate =null;  
		try {startDate = new SimpleDateFormat("yyyy-MM").parse(month_standard);	}
		catch(Exception e) {System.out.println("error");}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		//2017-8 
		int select_year = calendar.get(Calendar.YEAR);  //select Year   - 2017
		int select_month = calendar.get(Calendar.MONTH); //select month - 7

		String search_start=null;
		String search_end=null;
		if(start_date <= 15) { //시작일이 15일 이전인경우
			search_start = select_year+"-"+(select_month+1)+"-"+start_date; //2017-7-1
			
			if(start_date==1) { //시작일이 1인인경우
				calendar.set(select_year, select_month, 1);
				int dayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				search_end=select_year+"-"+(1+select_month)+"-"+dayofMonth;
			}
			
			else { //2~15
				if(select_month+2 == 13)
					search_end = (1+select_year)+"-1-"+(start_date-1);
				else
						search_end = select_year+"-"+(select_month+2)+"-"+(start_date-1);
			}
		}
		else { //15일이후
			if(select_month==0) //1월선택
				search_start=(select_year-1)+"-12-"+start_date;
			else 
				search_start=select_year+"-"+select_month+"-"+start_date;
			search_end=select_year+"-"+(select_month+1)+"-"+(start_date-1);
		}
		
		System.out.println("month_standard : "+month_standard);
		System.out.println("startDate : "+search_start+" / endDate : "+search_end);
		
		moneyList=moneyService.getMonthMoneyInfoFromStandard(search_start, search_end, user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}

		map.put("startDate", search_start);	
		map.put("endDate", search_end);	
		
		return new ModelAndView("jsonView", map);
	}
	

	@PostMapping("calendar.do")
	public ModelAndView doCalendar(@RequestBody String json, HttpSession session){   

		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login"); 
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		String user_id=userInfo.getUser_id();

		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String month_standard = (String) jsonObject.get("month_standard");   
		List<MoneyDto> moneyList = null;
		 
		int start_date=moneyService.getStartDate(user_id);
		Date startDate =null;  
		try {startDate = new SimpleDateFormat("yyyy-MM").parse(month_standard);	}
		catch(Exception e) {System.out.println("error");}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		//2017-8 
		int select_year = calendar.get(Calendar.YEAR);  //select Year   - 2017
		int select_month = calendar.get(Calendar.MONTH); //select month - 7

		
		
		
		String search_start=null;
		String search_end=null;
		

		String cal_search_start=null;
		String cal_search_end=null;
		if(start_date <= 15) { //시작일이 15일 이전인경우
			search_start = select_year+"-"+(select_month+1)+"-"+start_date; //2017-7-1 
			if(start_date==1) { //시작일이 1인인경우
				calendar.set(select_year, select_month, 1);
				int dayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				search_end=select_year+"-"+(1+select_month)+"-"+dayofMonth;
			}
			
			else { //2~15
				if(select_month+2 == 13)
					search_end = (1+select_year)+"-1-"+(start_date-1);
				else
						search_end = select_year+"-"+(select_month+2)+"-"+(start_date-1);
			}
			
			
			cal_search_start = select_year+"-"+(select_month+1)+"-1"; //2017-7-1
			calendar.set(select_year, select_month, 1);
			int caldayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal_search_end=select_year+"-"+(1+select_month)+"-"+caldayofMonth;
		}
		else { //15일이후
			if(select_month==0) { //1월선택
				search_start=(select_year-1)+"-12-"+start_date;
				cal_search_start = select_year+"-12-1"; //2017-7-1
			}
			else {
				cal_search_start = select_year+"-"+select_month+"-1"; //2017-7-1 
				search_start=select_year+"-"+select_month+"-"+start_date;
			}
			search_end=select_year+"-"+(select_month+1)+"-"+(start_date-1); 
			calendar.set(select_year, select_month, 1);
			int dayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal_search_end=select_year+"-"+(1+select_month)+"-"+dayofMonth;
		}
		  
		
		
		System.out.println("month_standard : "+month_standard);
		System.out.println("startDate : "+search_start+" / endDate : "+search_end);
		System.out.println("cal_search_start : "+cal_search_start+" / cal_search_end : "+cal_search_end);
		
		moneyList=moneyService.getMonthMoneyInfoFromStandard(cal_search_start, cal_search_end, user_id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}

		map.put("startDate", search_start);	
		map.put("endDate", search_end);	
		return new ModelAndView("jsonView", map);
	}

	@GetMapping("monthly.do")
	public String doMonthly(){  
		System.out.println("called monthly.do");
		return "monthly";
	}
	
	@GetMapping("calendar.do")
	public String doCalendar(){  
		System.out.println("called calendar.do");
		return "calendar";
	}
  
	@PostMapping("inputMoney.do")
	public void doInputMoney(HttpSession session, HttpServletRequest request,  HttpServletResponse response) throws IOException{ 
		System.out.println("called inputMoney.do");
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		
		if(userInfo!=null) {
			String category=request.getParameter("categorySelect");
			String inputMoney=request.getParameter("inputMoney");
			String use_date=request.getParameter("useDate"); 
			
			int retV=0;
			retV=moneyService.insertInputMoney(userInfo.getUser_id(), category, inputMoney, use_date);
			System.out.println("retv = "+retV);
		}
		response.sendRedirect("main2.do"); 
	} 
	
	@PostMapping("updateStartDate.do")
	public ModelAndView doUpdateStartDate(@RequestBody String json, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login");
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo"); 

		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String startDate = (String) jsonObject.get("monthlyStartDate");    
		//user_id �굹以묒뿉 �뵲濡�
		int retV=0; 
		retV=moneyService.updateStartDate(userInfo.getUser_id(), startDate);
		
		String message=null;
		if(retV==0)
			message="일시적인 시스템 오류가 발생하였습니다. 다시 입력하여 주세요^^"; 
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("message", message);	 
		return new ModelAndView("jsonView", map);
	}


	@GetMapping(value = "moneyResult.do")
	public ModelAndView doMoneyResult(HttpSession session){   
		System.out.println("called moneyResult.do");
		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login");
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		
		List<MoneyDto> moneyList = null;
		moneyList=moneyService.getMonthlyMoneyInfo(userInfo.getUser_id());
		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		} 
		return new ModelAndView("jsonView", map);
	}
	
	 
	 

	@PostMapping("deleteMonthSelected.do")
	public ModelAndView doDeleteMonthSelected(@RequestBody String json, HttpServletRequest request, HttpSession session) { 
		if (session.getAttribute("loginInfo") == null)  // LoginInfo exists in session
			return new ModelAndView("login");
		MemberDto userInfo=(MemberDto) session.getAttribute("loginInfo");
		
		//convert  to Set<String>
		Set<String> moneyIds=null;
		
		JSONObject jsonObject = null;
		JSONArray jsonArray = null; 
		
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
			jsonArray = (JSONArray) jsonObject.get("chk");
			
			int result = 0;
			List<String> deleteTargetList = new ArrayList<String>();
			
			for (int i = 0; i < jsonArray.size(); i++) {
				deleteTargetList.add((String)jsonArray.get(i));
			}
			
			moneyIds = deleteTargetList.stream()
					.map(id->id.trim())
					.filter(id->!id.isEmpty())
					.collect(Collectors.toSet());
		} catch (Exception e) {
			e.printStackTrace();
		} 
 
		int result=0;
		result=moneyService.deleteSelected(userInfo.getUser_id(), moneyIds);
		
		String message=null;
		if (result!=0) 
			message="성공적으로 삭제되었습니다."; 
		else 
			message="삭제를 다시 시도해주세요.";


		List<MoneyDto> moneyList = null;
		 
		int start_date=moneyService.getStartDate(userInfo.getUser_id());
		String month_standard = (String) jsonObject.get("month_standard");  
		Date startDate =null;  
		try {startDate = new SimpleDateFormat("yyyy-MM").parse(month_standard);	}
		catch(Exception e) {System.out.println("error");}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		//2017-8 
		int select_year = calendar.get(Calendar.YEAR);  //select Year   - 2017
		int select_month = calendar.get(Calendar.MONTH); //select month - 7

		String search_start=null;
		String search_end=null;
		if(start_date <= 15) { //시작일이 15일 이전인경우
			search_start = select_year+"-"+(select_month+1)+"-"+start_date; //2017-7-1
			
			if(start_date==1) { //시작일이 1인인경우
				calendar.set(select_year, select_month, 1);
				int dayofMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				search_end=select_year+"-"+(1+select_month)+"-"+dayofMonth;
			}
			
			else { //2~15
				if(select_month+2 == 13)
					search_end = (1+select_year)+"-1-"+(start_date-1);
				else
						search_end = select_year+"-"+(select_month+2)+"-"+(start_date-1);
			}
		}
		else { //15일이후
			if(select_month==0) //1월선택
				search_start=(select_year-1)+"-12-"+start_date;
			else 
				search_start=select_year+"-"+select_month+"-"+start_date;
			search_end=select_year+"-"+(select_month+1)+"-"+(start_date-1);
		}
		
		System.out.println("month_standard : "+month_standard);
		System.out.println("startDate : "+search_start+" / endDate : "+search_end);
		
		moneyList=moneyService.getMonthMoneyInfoFromStandard(search_start, search_end, userInfo.getUser_id());
		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		 
		
		map.put("message", message);
		return new ModelAndView("jsonView", map);
	}
	
	
	 
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

}