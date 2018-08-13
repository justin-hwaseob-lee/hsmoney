package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.dto.MainInfoDto;
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
	public ModelAndView doMain(){ 
		return func();
	}
	@GetMapping("main.do")
	public ModelAndView func() {
		return MainCommonCode(null);
	}
	
	@GetMapping("login.do")
	public String doLogin(){  
		return "login";
	}

	@GetMapping("monthly.do")
	public String doMonthly(){  
		return "monthly";
	}
 
	@PostMapping("inputMoney.do")
	public ModelAndView doInputMoney(HttpSession session, HttpServletRequest request) { 
		String category=request.getParameter("categorySelect");
		String inputMoney=request.getParameter("inputMoney");
		String use_date=request.getParameter("useDate"); 
		
		//user_id 나중에 따로
		String user_id="1";
		int retV=0;
		retV=moneyService.insertInputMoney(user_id, category, inputMoney, use_date);
		System.out.println("retv = "+retV);
		String message=null;
		if(retV==0)
			message="일시적인 시스템 오류가 발생하였습니다. 다시 입력하여 주세요^^"; 
		
		//main.do와 동일
		return MainCommonCode(message);
	}
	
	@PostMapping("/updateStartDate.do")
	public ModelAndView doUpdateStartDate(HttpSession session, HttpServletRequest request) {
		String startDate=request.getParameter("monthlyStartDate"); 
		//user_id 나중에 따로
		int retV=0;
		String user_id="1"; 
		retV=moneyService.updateStartDate(user_id, startDate);
		
		String message=null;
		if(retV==0)
			message="일시적인 시스템 오류가 발생하였습니다. 다시 입력하여 주세요^^";
		
		return MainCommonCode(message);
	}

	@PostMapping("/deleteSelected.do")
	public ModelAndView doDeleteSelected(@RequestBody String json, HttpServletRequest request) {

		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		boolean flag = false;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
			jsonArray = (JSONArray) jsonObject.get("chk");
			
			int result = 0;
			for (int i = 0; i < jsonArray.size(); i++) {
				String money_id = (String) jsonArray.get(i); 
				result = moneyService.deleteSelected(money_id);
				System.out.println("result : "+result);
				if (result != 1) {
					flag = true;
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
 

		
		
		String message=null;
		if (flag) 
			message="삭제를 다시 시도해 주세요."; 
		else 
			message="성공적으로 삭제되었습니다.";


		List<MoneyDto> moneyList = null;
		moneyList=moneyService.getAllMoneyInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		

		String monthlyTotal=moneyService.getMonthTotal();
		map.put("monthlyTotal", monthlyTotal);
		System.out.println("monthlyTotal : "+monthlyTotal);
		
		map.put("message", message);
		return new ModelAndView("jsonView", map);
	}
	
	public ModelAndView MainCommonCode(String message) { 
		//main.do와 동일
		ModelAndView mav=new ModelAndView();
		mav.setViewName("main"); 
		MainInfoDto mainInfo=null;
		mainInfo=moneyService.getMainInfo();
		if(mainInfo!=null) {
			mav.addObject("message", message);
			mav.addObject("startDate", mainInfo.getStart_date());
			mav.addObject("monthlyTotal", mainInfo.getMonthly_use());
			mav.addObject("dailyTotal", mainInfo.getDaily_use());
		}
		else {
			mav.addObject("monthlyTotal", "0");
			mav.addObject("dailyTotal", "0");
		}
		return mav;
	}
	@GetMapping(value = "moneyResult.do")
	public ModelAndView doMoneyResult(){   
		List<MoneyDto> moneyList = null;
		moneyList=moneyService.getAllMoneyInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if(moneyList!=null){
			map.put("moneyList", moneyList);	
		}
		String monthlyTotal=moneyService.getMonthTotal();
		map.put("monthlyTotal", monthlyTotal);
		System.out.println("monthlyTotal : "+monthlyTotal);
		return new ModelAndView("jsonView", map);
	}
	
	
	
	//이거안해줘서 삽질함 꼭필요
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

}

