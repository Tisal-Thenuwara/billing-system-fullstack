package com.base.billing.ms.service.impl;

import com.base.billing.ms.model.dao.Customer;
import com.base.billing.ms.repository.CustomerRepository;
import com.base.billing.ms.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByAccountNumber(String accountNumber) {
        return customerRepository.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional
    public void updateCustomerAddress(String accountNumber, String addressLine1, String addressLine2, String addressLine3, String city, String postalCode) {
        customerRepository.updateAddressLine1AndAddressLine2AndAddressLine3AndCityAndPostalCodeByAccountNumber(addressLine1, addressLine2, addressLine3, city, postalCode, accountNumber);
    }

    @Override
    @Transactional
    public void updateCustomerInfo(String accountNumber, String firstName, String lastName, String phone, String email) {
        customerRepository.updateFirstNameAndLastNameAndPhoneAndEmailByAccountNumber(firstName, lastName, phone, email, accountNumber);
    }

    @Override
    @Transactional
    public void updateUnitsConsumed(String accountNumber, int unitsConsumed) {
        customerRepository.updateUnitsConsumedByAccountNumber(unitsConsumed, accountNumber);
    }

    @Override
    public void deleteCustomer(String accountNumber) {
        customerRepository.deleteByAccountNumber(accountNumber);
    }
}
