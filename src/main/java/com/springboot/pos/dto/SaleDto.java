package com.springboot.pos.dto;

import com.springboot.pos.model.CashPayment;
import com.springboot.pos.model.Sale;
import com.springboot.pos.model.SaleItem;
import com.springboot.pos.model.Taxable;

import java.util.*;

public class SaleDto {

    private String saleNumber;
    private CashierDto cashier;
    private Date transactionDate;
    private List<SaleItemDto> saleItems;
    private Taxable tax;
    private double cashInHand;

    public SaleDto(Sale sale) {
        this.saleNumber = sale.getSaleNumber();
        this.cashier = new CashierDto(sale.getCashier());
        this.transactionDate = sale.getTransactionDate();
        this.tax = new Taxable();
        if (sale.getPayment() instanceof CashPayment) {
            this.cashInHand = ((CashPayment) sale.getPayment()).getCashInHand();
        }
        this.saleItems = new ArrayList<>();
        for (SaleItem saleItem : sale.getSaleItems()) {
            this.saleItems.add(new SaleItemDto(saleItem));
        }
    }

    public SaleDto(CashierDto cashierDto) {
        this.saleNumber = UUID.randomUUID().toString();
        this.cashier = cashierDto;
        this.tax = new Taxable();
        this.saleItems = new ArrayList<>();
    }

    public SaleDto() {
        this.saleNumber = UUID.randomUUID().toString();
        this.saleItems = new ArrayList<>();
    }



    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public CashierDto getCashier() {
        return cashier;
    }

    public void setCashier(CashierDto cashier) {
        this.cashier = cashier;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<SaleItemDto> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItemDto> saleItems) {
        this.saleItems = saleItems;
    }

    public double getCashInHand() {
        return cashInHand;
    }

    public void setCashInHand(double cashInHand) {
        this.cashInHand = cashInHand;
    }

    public void addSaleItem(SaleItemDto saleItem) {
        boolean itemAlreadyExists = false;
        for (SaleItemDto existingItem : this.saleItems) {
            if (existingItem.getItem().getItemCode().equals(saleItem.getItem().getItemCode())) {
                existingItem.setQuantity(existingItem.getQuantity() + saleItem.getQuantity());
                itemAlreadyExists = true;
                break;
            }
        }
        if (!itemAlreadyExists) {
            saleItems.add(saleItem);
        }
    }

    public void deleteSaleItem(String id) {
        Iterator<SaleItemDto> iterator = this.saleItems.iterator();
        while (iterator.hasNext()) {
            SaleItemDto existingItem = iterator.next();
            if (existingItem.getId().equals(id)) {
                iterator.remove();
            }
        }
    }

    public double totalPrice() {
        return totalPriceWithoutTax() + taxAmount();
    }

    public double taxAmount() {
        double taxAmount = 0;
        for (SaleItemDto saleItem : saleItems) {
            if (saleItem.getItem().isTaxable()) {
                taxAmount = taxAmount + (saleItem.totalPrice() * tax.getTax());
            }
        }
        return taxAmount;
    }

    public double totalPriceWithoutTax() {
        double totalPriceWithoutTax = 0;
        for (SaleItemDto saleItem : saleItems) {
            totalPriceWithoutTax = totalPriceWithoutTax + saleItem.totalPrice();
        }
        return totalPriceWithoutTax;
    }

    public double change() {
        return this.cashInHand - totalPrice();
    }

}
