package com.springboot.pos.dto;

public class SavedCashierDto {
    private String username;
    private String name;

    public SavedCashierDto(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public SavedCashierDto() {
    }

    public SavedCashierDto(CashierDto save) {
        this.name = save.getName();
        this.username = save.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
