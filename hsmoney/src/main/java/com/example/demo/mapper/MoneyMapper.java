package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.MainInfoDto;
import com.example.demo.dto.MoneyDto;

@Mapper
public interface MoneyMapper { 
	public List<MoneyDto> getAllMoneyInfo();
	
	public MainInfoDto getMainInfo();
	
	public int insertInputMoney(@Param("user_id") String user_id,  
			@Param("category") String category, 
			@Param("inputMoney") String inputMoney,
			@Param("use_date") String use_date);
	
	public int updateStartDate(@Param("user_id") String user_id,
			@Param("startDate") String startDate);
	
	public int deleteSelected(@Param("money_id") String money_id);
	
	public String getMonthTotal();
}
