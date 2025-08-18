package com.pahanaedu.service;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;

import java.math.BigDecimal;

public class BillingService {
    private final CustomerDAO customerDAO = new CustomerDAO();

    // Simple flat rate; change to fetch from DB if needed
    private static final BigDecimal PRICE_PER_UNIT = new BigDecimal("50.00");

    public Bill generateBill(int accountNo) {
        Customer c = customerDAO.get(accountNo);
        if (c == null) return null;
        return new Bill(accountNo, c.getUnitsConsumed(), PRICE_PER_UNIT);
    }
}
