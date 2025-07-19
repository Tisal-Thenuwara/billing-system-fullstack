package com.base.billing.ms.service;

import com.base.billing.ms.model.dao.Customer;

public interface CustomerService {
    Customer getCustomerByAccountNumber(String accountNumber);

    void updateCustomerAddress(String accountNumber, String addressLine1, String addressLine2, String addressLine3, String city, String postalCode);

    void updateCustomerInfo(String accountNumber, String firstName, String lastName, String phone, String email);

    void updateUnitsConsumed(String accountNumber, int unitsConsumed);

    void deleteCustomer(String accountNumber);
}
