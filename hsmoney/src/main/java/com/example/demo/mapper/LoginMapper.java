package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.MemberDto;

@Mapper
public interface LoginMapper {

	public MemberDto getLoginInfo(@Param("name") String name, @Param("pw") String pw);

	public int register(@Param("name") String name, @Param("pw") String pw);
	
	public String checkName(@Param("name") String name);
	
	public int updateUserInfo(@Param("user_id") String user_id, @Param("pw") String pw);
}
