package com.springboot.pos.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("QRIS")
public class QrisPayment extends Payment{
	
	public QrisPayment() {
	}
	
}