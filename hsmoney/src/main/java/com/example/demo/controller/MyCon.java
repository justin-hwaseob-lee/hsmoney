package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyCon {
	
	@GetMapping("/main")
	public String func() {
		System.out.println("calledll");
		return "main";
	}
}
