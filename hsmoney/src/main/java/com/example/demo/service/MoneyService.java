package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
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
	public List<MoneyDto> getMonthlyMoneyInfo(String user_id){
		return moneyMapper.getMonthlyMoneyInfo(user_id).stream()
				.collect(Collectors.toList());
	} 
	
	@Transactional
	public List<MoneyDto> getMonthMoneyInfoFromStandard(String search_start, String search_end, String user_id){
		return moneyMapper.getMonthMoneyInfoFromStandard(search_start, search_end, user_id).stream()
				.collect(Collectors.toList());
	}
	
	@Transactional
	public List<MoneyDto> getYearMoneyInfoFromStandard(String selectYear, String user_id){
		return moneyMapper.getYearMoneyInfoFromStandard(selectYear, user_id).stream()
				.collect(Collectors.toList());
	}
	
	
	@Transactional
	public MainInfoDto getMainInfo(String user_id){
		return moneyMapper.getMainInfo(user_id);
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
	public int deleteSelected(String user_id, Set<String> moneyIds){
		return moneyMapper.deleteSelected(user_id, moneyIds);
	}

	//@Transactional
	//public String getMonthTotal() {
	//	return moneyMapper.getMonthTotal();
	//}
	
	@Transactional
	public int getStartDate(String user_id) {
		return Integer.parseInt(moneyMapper.getStartDate(user_id));
	}
}