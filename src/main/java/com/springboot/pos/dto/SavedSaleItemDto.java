package com.springboot.pos.dto;

import com.springboot.pos.model.SaleItem;

public class SavedSaleItemDto {
    private String itemDescription;
    private int quantity;
    private double itemPrice;

    public SavedSaleItemDto(String itemDescription, int quantity, double itemPrice) {
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public SavedSaleItemDto() {
    }

    public SavedSaleItemDto(SaleItem saleItem) {
        this.itemDescription = saleItem.getItem().getDescription();
        this.itemPrice = saleItem.getPrice();
        this.quantity =saleItem.getQuantity();
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
