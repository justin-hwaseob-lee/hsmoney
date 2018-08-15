package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public String doLogin(){  
		return "login";
	}
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
	
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.setAttribute("loginInfo", null); 
		return "login";
	}
}
