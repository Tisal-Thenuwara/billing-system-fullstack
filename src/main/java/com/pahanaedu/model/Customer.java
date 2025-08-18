package com.pahanaedu.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private int accountNo;
    private String name;
    private String address1;
    private String address2;
    private String phone;
    private String email;
    private int unitsConsumed;

    public Customer() {}

    public Customer(int accountNo, String name, String address1, String address2, String phone, String email, int unitsConsumed) {
        this.accountNo = accountNo;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.phone = phone;
        this.email = email;
        this.unitsConsumed = unitsConsumed;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }
}
