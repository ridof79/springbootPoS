package com.springboot.pos.model;

import com.springboot.pos.util.Constants;

public class Taxable {
	private double tax = Constants.TAX_PERCENTAGE;

	public double getTax() {
		return (tax / 100.0);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	
}
