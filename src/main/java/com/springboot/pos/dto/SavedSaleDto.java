package com.springboot.pos.dto;

import com.springboot.pos.model.CashPayment;
import com.springboot.pos.model.Sale;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SavedSaleDto {
    private String saleNumber;
    private String cashierName;
    private Date transactionDate;
    private List<SavedSaleItemDto> saleItems;
    private double cashInHande;

    public SavedSaleDto(String saleNumber, String cashierName, Date transactionDate, List<SavedSaleItemDto> saleItems, double cashInHande) {
        this.saleNumber = saleNumber;
        this.cashierName = cashierName;
        this.transactionDate = transactionDate;
        this.saleItems = saleItems;
        this.cashInHande = cashInHande;
    }

    public SavedSaleDto() {
    }

    public SavedSaleDto(Sale sale) {
        this.saleNumber = sale.getSaleNumber();
        this.cashierName = sale.getCashier().getName();
        this.transactionDate = sale.getTransactionDate();
        this.saleItems = sale.getSaleItems().stream().map(SavedSaleItemDto::new).collect(Collectors.toList());
        if(sale.getPayment() instanceof CashPayment) {
            this.cashInHande = ((CashPayment) sale.getPayment()).getCashInHand();
        }
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<SavedSaleItemDto> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SavedSaleItemDto> saleItems) {
        this.saleItems = saleItems;
    }

    public double getCashInHande() {
        return cashInHande;
    }

    public void setCashInHande(double cashInHande) {
        this.cashInHande = cashInHande;
    }
}


