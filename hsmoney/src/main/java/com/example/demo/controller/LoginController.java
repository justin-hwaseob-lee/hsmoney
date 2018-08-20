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
	

	@GetMapping("userinfo.do")
	public ModelAndView userInfo(HttpSession session) { 
		ModelAndView mav=new ModelAndView();
		if (session.getAttribute("loginInfo") == null) {  // LoginInfo exists in session
			mav.setViewName("login"); 
			return mav;
		} 
		mav.setViewName("userinfo"); 
		MemberDto member=(MemberDto) session.getAttribute("loginInfo");
		mav.addObject("name",member.getUser_name());
		return mav;
	} 

	@GetMapping("register")
	public String doRegister(HttpSession session) { 
		return "register"; 
	} 
	
	@PostMapping("register.do")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		String name=(String)request.getParameter("form-username");
		String pw=(String)request.getParameter("form-password");
		int res=0;
		String check=loginService.checkName(name);
		System.out.println("Register:"+check);
		if(check!=null) //실패시
			res=0;
		else
			res=loginService.register(name, pw);
		
		if(res==1) { //성공
			mav.setViewName("login");
			mav.addObject("message", "성공적으로 등록되었습니다. 로그인 하세요^^");
		}
		else {
			mav.setViewName("register");
			mav.addObject("message", "존재하는 이름입니다. 새로운 이름으로 가입하여 	주세요."); 
		} 
		return mav;
	}
	
	@PostMapping("modifyUser.do")
	public ModelAndView modifyUser(HttpSession session, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();  
		mav.setViewName("main");
		
		String pw=(String)request.getParameter("form-password");
		String user_id=((MemberDto)session.getAttribute("loginInfo")).getUser_id();
		int res=0;
		res=loginService.updateUserInfo(user_id, pw);

		MemberDto member=(MemberDto) session.getAttribute("loginInfo");
		mav.addObject("name",member.getUser_name());
		if(res==1)   //성공 
			mav.addObject("message", "비밀번호가 성공적으로 수정 되었습니다.^^"); 
		else 
			mav.addObject("message", "일시적인 시스템 오류입니다. 다시 시도해 주세요.");  
		return mav; 
	}
	
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
			
			
			
//			session.setMaxInactiveInterval(20*60);// 20분 
			
			
		} else { // Login Failure  
			map.put("message", "ID/PW를 확인해주세요!");
		} 
		return new ModelAndView("jsonView", map);
	}
	
	@RequestMapping(value = "logout.do")
	//public void logout(HttpSession session,   HttpServletResponse response) throws IOException{
	public ModelAndView logout(HttpSession session,  HttpServletResponse response) throws IOException{
		ModelAndView mav=new ModelAndView();
		session.setAttribute("loginInfo", null); 

		mav.setViewName("login");
		mav.addObject("logout","true");
		//response.sendRedirect("login.do"); 
		return mav;
	}
	

	//이거안해줘서 삽질함 꼭필요
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}