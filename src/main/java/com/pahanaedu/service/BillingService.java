package com.pahanaedu.service;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;

public class BillingService {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final BillDAO billDAO = new BillDAO();


    public boolean saveBill(Bill bill) {
        boolean isCreated = billDAO.createBill(bill);
        if (isCreated) {
            //get units and update
            Customer c = customerDAO.get(bill.getAccountNo());
            if (c == null) return false;
            bill.setUnits(c.getUnitsConsumed() + bill.getUnits());
            customerDAO.updateUnits(bill.getAccountNo(), bill.getUnits());
            return isCreated;
        }
        return false;
    }
}
