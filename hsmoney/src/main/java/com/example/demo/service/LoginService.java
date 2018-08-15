package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MemberDto;
import com.example.demo.mapper.LoginMapper;

@Service
public class LoginService {

	@Autowired
	LoginMapper logginMapper;
	
	@Transactional
	public MemberDto getLoginInfo(String name, String pw) {
		return logginMapper.getLoginInfo(name, pw);
	}
}
