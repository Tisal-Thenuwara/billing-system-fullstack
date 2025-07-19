package com.base.billing.ms.service;

import com.base.billing.ms.model.dao.Customer;
import com.base.billing.ms.model.dao.Item;
import com.base.billing.ms.model.dao.User;
import com.base.billing.ms.model.dto.BillDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BillService {
    List<BillDto> getBillsByCustomerAccount(String accountNumber);

    List<BillDto> getBillsByCustomer(Customer customer);

    List<BillDto> getBillsByItem(Item item);

    List<BillDto> getBillsByUser(User user);

    List<BillDto> getBillsByStatus(String status);

    void createBill(Customer customer, int units, double amount, String status, LocalDateTime createdDate, Item item, User user);

    void updateBill(Long billId, int units, String status, double amount, LocalDateTime updatedDate, Item item);

    void deleteBill(Long billId);
}
