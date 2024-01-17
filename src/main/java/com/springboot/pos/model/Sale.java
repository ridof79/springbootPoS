package com.springboot.pos.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale_tbl")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "sale_number")
	private String saleNumber;

	@ManyToOne
	@JoinColumn(name = "cashier_id")
	private Cashier cashier;

	private Date transactionDate;

	@OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
	private List<SaleItem> saleItems;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@Transient
	private Taxable tax;
	
	public Sale(String saleNumber, Cashier cashier, Date transactionDate, List<SaleItem> saleItems, Payment payment, Taxable tax) {
		this.saleNumber = saleNumber;
		this.cashier = cashier;
		this.transactionDate = transactionDate;
		this.saleItems = saleItems;
		this.payment = payment;
		this.tax = tax;
	}
	
	public Sale() {
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<SaleItem> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Taxable getTax() {
		return tax;
	}

	public void setTax(Taxable tax) {
		this.tax = tax;
	}
	
}
