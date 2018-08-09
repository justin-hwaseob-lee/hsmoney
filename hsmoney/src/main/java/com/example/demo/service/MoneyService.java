package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MoneyDto;
import com.example.demo.mapper.MoneyMapper;

@Service
public class MoneyService {
	
	@Autowired
	private MoneyMapper moneyMapper;
	
	public List<MoneyDto> getAllMoneyInfo(){
		System.out.println("justin : service start");
		return moneyMapper.getAllMoneyInfo().stream()
				.collect(Collectors.toList());
	}
}
