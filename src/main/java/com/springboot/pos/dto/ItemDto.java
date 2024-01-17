package com.springboot.pos.dto;


import com.springboot.pos.model.Item;

public class ItemDto {
	
	private String id;
	private String itemCode;
	private double price;
	private String description;
	private String type;
	private boolean taxable;
	
	public ItemDto(String itemCode, double price, String description, String type, boolean taxable) {
		this.itemCode = itemCode;
		this.price = price;
		this.description = description;
		this.type = type;
		this.taxable = taxable;
	}
	
	public ItemDto(Item item) {
		this.id = item.getId();
		this.itemCode = item.getItemCode();
		this.price = item.getPrice();
		this.description = item.getDescription();
		this.type = item.getType();
		this.taxable = item.isTaxable();
	}
	
	public ItemDto() {
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getPrice() {
		return price;
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
	
	

}
