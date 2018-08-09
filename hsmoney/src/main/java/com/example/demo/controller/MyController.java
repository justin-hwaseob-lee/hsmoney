package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.dto.MoneyDto;
import com.example.demo.service.MoneyService;
 

@Controller
public class MyController {

	@Autowired
	private MoneyService moneyService;

	@GetMapping("/")
	public String doMain(){
		System.out.println("justin : / called");
		return "login";
	}
	@GetMapping("main.do")
	public String func() {
		return "main";
	}
	
	@GetMapping("login.do")
	public String doLogin(){ 
		System.out.println("justin : /login.do called");
		return "login";
	}
	

	//@PostMapping("/moneyResult.do")

	@GetMapping(value = "moneyResult.do")
	public ModelAndView doMoneyResult(){  
		System.out.println("justin : /moneyresult.do start called");
		List<MoneyDto> moneyList = moneyService.getAllMoneyInfo();
		System.out.println("justin : /moneyresult.do end called");
		System.out.println(moneyList.get(0).getUser_id()+moneyList.get(0).getUser_name()+moneyList.get(0).getUser_pw()+moneyList.get(0).getStart_date()+moneyList.get(0).getMoney_id()+moneyList.get(0).getPrice()+moneyList.get(0).getCategory()+moneyList.get(0).getUse_date());
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moneyList", moneyList);
		return new ModelAndView("jsonView", map);
	}
	
	//이거안해줘서 삽질함 꼭필요
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

}
