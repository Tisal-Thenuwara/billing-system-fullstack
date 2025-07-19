package com.base.billing.ms.controller;

import com.base.billing.ms.model.dao.*;
import com.base.billing.ms.model.dto.BillDto;
import com.base.billing.ms.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/customer-account/{accountNumber}")
    public List<BillDto> getBillsByCustomerAccount(@PathVariable String accountNumber) {
        return billService.getBillsByCustomerAccount(accountNumber);
    }

    @GetMapping("/customer")
    public List<BillDto> getBillsByCustomer(@RequestBody Customer customer) {
        return billService.getBillsByCustomer(customer);
    }

    @GetMapping("/item")
    public List<BillDto> getBillsByItem(@RequestBody Item item) {
        return billService.getBillsByItem(item);
    }

    @GetMapping("/user")
    public List<BillDto> getBillsByUser(@RequestBody User user) {
        return billService.getBillsByUser(user);
    }

    @GetMapping("/status/{status}")
    public List<BillDto> getBillsByStatus(@PathVariable String status) {
        return billService.getBillsByStatus(status);
    }

    @PostMapping
    public void createBill(@RequestBody Bill bill) {
        billService.createBill(
            bill.getCustomer(), 
            bill.getUnits(), 
            bill.getAmount(), 
            bill.getStatus(), 
            bill.getCreatedDate(), 
            bill.getItem(), 
            bill.getUser()
        );
    }

    @PutMapping("/{billId}")
    public void updateBill(@PathVariable Long billId, @RequestBody Bill updatedBill) {
        billService.updateBill(
            billId, 
            updatedBill.getUnits(), 
            updatedBill.getStatus(), 
            updatedBill.getAmount(), 
            updatedBill.getUpdatedDate(), 
            updatedBill.getItem()
        );
    }

    @DeleteMapping("/{billId}")
    public void deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
    }
}
