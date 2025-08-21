package com.pahanaedu.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bill implements Serializable {
    private int billId;
    private int accountNo;
    private int itemId;
    private int units;
    private BigDecimal total;
    private BigDecimal pricePerUnit;


    public Bill(){}

    public Bill(int billId, int accountNo, int itemId, int units, BigDecimal total, BigDecimal pricePerUnit) {
        this.billId = billId;
        this.accountNo = accountNo;
        this.itemId = itemId;
        this.units = units;
        this.total = total;
        this.pricePerUnit = pricePerUnit;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


    public int getAccountNo() { return accountNo; }
    public void setAccountNo(int accountNo) { this.accountNo = accountNo; }
    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }
    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
