package com.base.billing.ms.service.impl;

import com.base.billing.ms.model.dao.Customer;
import com.base.billing.ms.model.dao.Item;
import com.base.billing.ms.model.dao.User;
import com.base.billing.ms.model.dto.BillDto;
import com.base.billing.ms.repository.BillRepository;
import com.base.billing.ms.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDto> getBillsByCustomerAccount(String accountNumber) {
        return billRepository.findAllByCustomerAccountNumber(accountNumber);
    }

    @Override
    public List<BillDto> getBillsByCustomer(Customer customer) {
        return billRepository.findAllByCustomer(customer);
    }

    @Override
    public List<BillDto> getBillsByItem(Item item) {
        return billRepository.findAllByItem(item);
    }

    @Override
    public List<BillDto> getBillsByUser(User user) {
        return billRepository.findAllByUser(user);
    }

    @Override
    public List<BillDto> getBillsByStatus(String status) {
        return billRepository.findAllByStatus(status);
    }

    @Override
    @Transactional
    public void createBill(Customer customer, int units, double amount, String status, LocalDateTime createdDate, Item item, User user) {
        billRepository.createBill(customer, units, amount, status, createdDate, item, user);
    }

    @Override
    @Transactional
    public void updateBill(Long billId, int units, String status, double amount, LocalDateTime updatedDate, Item item) {
        billRepository.updateUnitsAndStatusAndAmountAndUpdatedDateAndItemByBillId(units, status, amount, updatedDate, item, billId);
    }

    @Override
    @Transactional
    public void deleteBill(Long billId) {
        billRepository.deleteByBillId(billId);
    }
}
