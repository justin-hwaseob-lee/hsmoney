package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.MemberDto;

@Mapper
public interface LoginMapper {

	public MemberDto getLoginInfo(@Param("name") String name, @Param("pw") String pw);
}
