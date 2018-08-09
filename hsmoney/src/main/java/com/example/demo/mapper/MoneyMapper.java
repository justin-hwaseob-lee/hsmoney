package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MoneyDto;

@Mapper
public interface MoneyMapper { 
	public List<MoneyDto> getAllMoneyInfo();
}
