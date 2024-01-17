package com.springboot.pos.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "payment_id")
	private String id;

	
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_number")
	protected Sale sale;
	
	protected double amount;
	
	@Transient
	protected boolean isPay = false;

	public void validate() {
		this.isPay = true;
	}

	public double getAmount() {
		return this.amount;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	public String getId() {
		return id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
