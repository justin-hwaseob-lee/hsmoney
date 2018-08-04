package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyCon {
	
	@GetMapping("/main.do")
	public String func() {
		return "main";
	}
	
	@GetMapping("/login.do")
	public String doLogin(){
		
		return "login";
	}
}
