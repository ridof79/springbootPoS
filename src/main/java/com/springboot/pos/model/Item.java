package com.springboot.pos.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="item_tbl")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "item_id")
	private String id;
	
	@Column(name = "item_code", unique = true, nullable = false)
	private String itemCode;
	
	@Column(nullable = false)
	private double price;
	
	@Column(unique = true, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String type;
	@Column(nullable = true)
	private boolean taxable;
	
	public Item(String id, String itemCode, double price, String description, String type, boolean taxable) {
		this.id = id;
		this.itemCode = itemCode;
		this.price = price;
		this.description = description;
		this.type = type;
		this.taxable = taxable;
	}

	public Item() {
	}

	public double getPrice() {
		return price;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public String getItemCode() {
		return itemCode;
	}
	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
}
