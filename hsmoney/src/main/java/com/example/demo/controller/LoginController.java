package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("login")
	public ModelAndView doLogin(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");

		if (session.getAttribute("loginInfo") != null) {
			mav = new ModelAndView("main");
			return mav;
		}
		return mav;
	}

	@GetMapping("login.do")
	public ModelAndView doLogin( ){  
		System.out.println("\ncalled login.do get");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("logout","true");
		return mav;
	}
	
	/*
	@PostMapping("login.do")
	public ModelAndView login(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");

		if (session.getAttribute("loginInfo") != null) { // LoginInfo exists in
															// session
			mav = new ModelAndView("main");
			return mav;
		} else {

			String name = request.getParameter("form-username");
			String pw = request.getParameter("form-password"); 
			System.out.println("name : "+name+" / pw : "+pw);

			MemberDto loginInfo = null;
			loginInfo = loginService.getLoginInfo(name,pw);
			 

			if (loginInfo != null && loginInfo.getUser_name().equals(name)) {
				// Login  success
				session.setAttribute("loginInfo", loginInfo);
				mav = new ModelAndView("main");
			} else { // Login Failure 
				mav.addObject("message", "ID/PW를 확인해주세요!");
			}
			return mav;
		}
	}
	*/

	@PostMapping("login.do")
	public ModelAndView login(HttpSession session, HttpServletResponse response, @RequestBody String json) throws Exception {
		System.out.println("\ncalled login.do post"); 
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String name = (String) jsonObject.get("form-username");  
		String pw = (String) jsonObject.get("form-password");  
		System.out.println("name : "+name+" / pw : "+pw);


		MemberDto loginInfo = null;
		loginInfo = loginService.getLoginInfo(name,pw); 
		
		if(loginInfo!=null)
		System.out.println("loginINfo : "+loginInfo.getUser_id() +" / "+loginInfo.getUser_name());
		
		Map<String, Object> map = new HashMap<String, Object>();  
		if (loginInfo != null && loginInfo.getUser_name().equals(name)) {
			// Login  success
			session.setAttribute("loginInfo", loginInfo); 
			System.out.println("loginid : "+loginInfo.getUser_id());
		} else { // Login Failure 
			System.out.println("put mesage here");
			map.put("message", "ID/PW를 확인해주세요!");
		}
		System.out.println("success? : "+(String)map.get("message"));
		ModelAndView mav=new ModelAndView("jsonView", map);
		return mav;
		//return new ModelAndView("jsonView", map);
	}
	
	@RequestMapping(value = "logout.do")
	public void logout(HttpSession session,   HttpServletResponse response) throws IOException{
		session.setAttribute("loginInfo", null); 

		response.sendRedirect("login.do"); 
	}
	

	//이거안해줘서 삽질함 꼭필요
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}
