package com.example.demo.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.MainInfoDto;
import com.example.demo.dto.MoneyDto;

@Mapper
public interface MoneyMapper { 
	public List<MoneyDto> getAllMoneyInfo();
	public List<MoneyDto> getMonthlyMoneyInfo(@Param("user_id")String user_id);
	public MainInfoDto getMainInfo(@Param("user_id")String user_id);

	public String getStartDate(@Param("user_id")String user_id);
	
	public List<MoneyDto> getMonthMoneyInfoFromStandard(@Param("search_start")String search_start, @Param("search_end") String search_end, @Param("user_id") String user_id);
	
	public int insertInputMoney(@Param("user_id") String user_id,  
			@Param("category") String category, 
			@Param("inputMoney") String inputMoney,
			@Param("use_date") String use_date);
	
	public int updateStartDate(@Param("user_id") String user_id,
			@Param("startDate") String startDate);

	public int deleteSelected(@Param("user_id") String user_id, @Param("moneyIds") Set<String> moneyIds);
	
	//public String getMonthTotal();
}
