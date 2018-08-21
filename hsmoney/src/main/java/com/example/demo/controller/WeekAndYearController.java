package com.example.demo.controller;

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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MoneyDto;
import com.example.demo.service.MoneyService;

@Controller
public class WeekAndYearController {

	@Autowired
	private MoneyService moneyService;
	
	@GetMapping("annually.do")
	public String doAnuually(HttpSession session){ 
		if (session.getAttribute("loginInfo") != null)  // LoginInfo exists in session
			return "annually";
		else
			return "login"; 
	}

	@GetMapping("weekly.do")
	public String doWeekly(HttpSession session){ 
		if (session.getAttribute("loginInfo") != null)  // LoginInfo exists in session
			return "weekly";
		else
			return "login"; 
	}

	@PostMapping("deleteWeekSelected.do")
	public ModelAndView doDeleteWeekSelected(@RequestBody String json, HttpServletRequest request, HttpSession session) { 
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

		
		String startWeekDate=(String)jsonObject.get("startDate");
		String endWeekDate=(String)jsonObject.get("endDate");
		

		List<MoneyDto> moneyList = null;
		moneyList=moneyService.getMonthMoneyInfoFromStandard(startWeekDate, endWeekDate, userInfo.getUser_id());

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		} 
		map.put("message", message);
		return new ModelAndView("jsonView", map);
	}

	@PostMapping("weekChange.do")
	public ModelAndView doMonthChange(@RequestBody String json, HttpSession session){   
System.out.println("called weekChange.do post");
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
		String startWeekDate=(String)jsonObject.get("startDate");
		String endWeekDate=(String)jsonObject.get("endDate");
		 System.out.println(startWeekDate+" / "+endWeekDate);
		List<MoneyDto> moneyList = null;
		  
		moneyList=moneyService.getMonthMoneyInfoFromStandard(startWeekDate, endWeekDate, user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		return new ModelAndView("jsonView", map);
	}
	

	@PostMapping("yearChange.do")
	public ModelAndView doYearChange(@RequestBody String json, HttpSession session){   
System.out.println("called yearChange.do post");
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
		String selectYear=""+jsonObject.get("selectYear"); 
		 System.out.println(selectYear );
		List<MoneyDto> moneyList = null;
		  
		moneyList=moneyService.getYearMoneyInfoFromStandard(selectYear, user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		return new ModelAndView("jsonView", map);
	}
	

	@PostMapping("deleteSelected.do")
	public ModelAndView doDeleteSelected(@RequestBody String json, HttpServletRequest request, HttpSession session) { 
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

		String selectYear=""+jsonObject.get("selectYear"); 
		List<MoneyDto> moneyList = null;
		moneyList=moneyService.getYearMoneyInfoFromStandard(selectYear, userInfo.getUser_id());

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		map.put("message", message);
		return new ModelAndView("jsonView", map);
	}
}