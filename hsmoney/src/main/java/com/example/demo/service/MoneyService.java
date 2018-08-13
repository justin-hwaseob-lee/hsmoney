package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MainInfoDto;
import com.example.demo.dto.MoneyDto;
import com.example.demo.mapper.MoneyMapper;

@Service
public class MoneyService {
	
	@Autowired
	private MoneyMapper moneyMapper;
	
	@Transactional
	public List<MoneyDto> getAllMoneyInfo(){
		return moneyMapper.getAllMoneyInfo().stream()
				.collect(Collectors.toList());
	}
	
	@Transactional
	public MainInfoDto getMainInfo(){
		return moneyMapper.getMainInfo();
	}
	
	@Transactional
	public int insertInputMoney(String user_id, String category, String inputMoney, String use_date) { 
		return moneyMapper.insertInputMoney(user_id, category, inputMoney, use_date);
	}
	
	@Transactional
	public int updateStartDate(String user_id, String startDate) {
		return moneyMapper.updateStartDate(user_id, startDate);
	}
	
	@Transactional
	public int deleteSelected(String money_id){
		return moneyMapper.deleteSelected(money_id);
	}

	@Transactional
	public String getMonthTotal() {
		return moneyMapper.getMonthTotal();
	}
}
