package com.springboot.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_item_tbl")
public class SaleItem {

    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "sale_item_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "sale_number")
    private Sale sale;
	
	public SaleItem(String id, Item item, int quantity, double price, Sale sale) {
		super();
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
		this.sale = sale;
	}

	public SaleItem() {
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public double totalPrice() {
		return this.quantity * this.price;
	}

	
}