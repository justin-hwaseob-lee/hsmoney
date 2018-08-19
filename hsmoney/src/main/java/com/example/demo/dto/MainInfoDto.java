package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MainInfoDto {
	private String user_id;
	private String user_name;
	private String start_date;
	private int monthly_use;
	private int daily_use;
	private String use_date;
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public int getMonthly_use() {
		return monthly_use;
	}
	public void setMonthly_use(int monthly_use) {
		this.monthly_use = monthly_use;
	}
	public int getDaily_use() {
		return daily_use;
	}
	public void setDaily_use(int daily_use) {
		this.daily_use = daily_use;
	}
	public String getUse_date() {
		return use_date;
	}
	public void setUse_date(String use_date) {
		this.use_date = use_date;
	}
	 
	 
}