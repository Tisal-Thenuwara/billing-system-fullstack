package com.pahanaedu.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
    private int itemId;
    private String itemName;
    private BigDecimal pricePerUnit;

    public Item() {}

    public Item(int itemId, String itemName, BigDecimal pricePerUnit) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.pricePerUnit = pricePerUnit;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }
}
