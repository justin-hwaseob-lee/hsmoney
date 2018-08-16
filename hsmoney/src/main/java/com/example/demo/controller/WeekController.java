package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
public class WeekController {

	@Autowired
	private MoneyService moneyService;
	
	@GetMapping("annually.do")
	public String doAnuually(HttpSession session){ 
		return "annually";
	}

	@GetMapping("weekly.do")
	public String doWeekly(HttpSession session){ 
		return "weekly";
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

		String month_standard = (String) jsonObject.get("month_standard");  
		List<MoneyDto> moneyList = null;
		 
		int start_date=moneyService.getStartDate(user_id); 
		moneyList=moneyService.getMonthMoneyInfoFromStandard("2018-08-01", "2018-08-30", user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		return new ModelAndView("jsonView", map);
	}
}
