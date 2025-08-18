package com.pahanaedu.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bill implements Serializable {
    private int accountNo;
    private int units;
    private BigDecimal pricePerUnit;
    private BigDecimal total;

    public Bill(){}

    public Bill(int accountNo, int units, BigDecimal pricePerUnit) {
        this.accountNo = accountNo;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.total = pricePerUnit.multiply(new BigDecimal(units));
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
