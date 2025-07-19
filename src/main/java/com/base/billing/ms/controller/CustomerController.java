package com.base.billing.ms.controller;

import com.base.billing.ms.model.dao.Customer;
import com.base.billing.ms.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{accountNumber}")
    public Customer getCustomerByAccountNumber(@PathVariable String accountNumber) {
        return customerService.getCustomerByAccountNumber(accountNumber);
    }

    @PutMapping("/{accountNumber}/address")
    public void updateCustomerAddress(@PathVariable String accountNumber, @RequestBody Customer customer) {
        customerService.updateCustomerAddress(
            accountNumber, 
            customer.getAddressLine1(), 
            customer.getAddressLine2(), 
            customer.getAddressLine3(), 
            customer.getCity(), 
            customer.getPostalCode()
        );
    }

    @PutMapping("/{accountNumber}/info")
    public void updateCustomerInfo(@PathVariable String accountNumber, @RequestBody Customer customer) {
        customerService.updateCustomerInfo(
            accountNumber, 
            customer.getFirstName(), 
            customer.getLastName(), 
            customer.getPhone(), 
            customer.getEmail()
        );
    }

    @PutMapping("/{accountNumber}/units")
    public void updateCustomerUnits(@PathVariable String accountNumber, @RequestParam int unitsConsumed) {
        customerService.updateUnitsConsumed(accountNumber, unitsConsumed);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteCustomer(@PathVariable String accountNumber) {
        customerService.deleteCustomer(accountNumber);
    }
}
