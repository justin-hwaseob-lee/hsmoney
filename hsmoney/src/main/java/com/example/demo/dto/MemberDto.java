package com.example.demo.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberDto {
	private String user_id;
	private String user_name;
	private String user_pw;
	private String start_date;

	public MemberDto(int user_id, String user_name, String user_pw, String start_date) {
		super();
		this.user_id = ""+user_id;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.start_date = start_date;
	}
	
	public MemberDto(String user_id, String user_name, String user_pw, String start_date) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.start_date = start_date;
	}
	public MemberDto(String user_name, String user_pw) {
		super();
		this.user_name = user_name;
		this.user_pw = user_pw;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	
}
