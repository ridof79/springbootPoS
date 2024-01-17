package com.springboot.pos.dto;

import com.springboot.pos.model.Cashier;

public class CashierDto {

	private String id;
	private String username;
	private String name;
	private String password;

	public CashierDto(String username, String name, String id) {
		this.id = id;
		this.username = username;
		this.name = name;
	}
	
	public CashierDto(Cashier cashier) {
		this.id = cashier.getId();
		this.username = cashier.getUsername();
		this.name = cashier.getName();
	}

	public CashierDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
