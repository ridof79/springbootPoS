package com.springboot.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name="cashier_tbl")
public class Cashier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "cashier_id")
	private String id;
	
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;

	public Cashier(String username, String name, String password) {
		this.username = username;
		this.name = name;
		this.password = password;
	}
	
	public Cashier() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}