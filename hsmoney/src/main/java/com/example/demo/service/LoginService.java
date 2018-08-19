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
	
	@Transactional
	public int register(String name, String pw) {
		return logginMapper.register(name, pw);
	}
	@Transactional
	public String checkName(String name) {
		return logginMapper.checkName(name);
	}
	
	@Transactional
	public int updateUserInfo(String user_id, String pw) {
		return logginMapper.updateUserInfo(user_id, pw);
	}
}