package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Bill;
import com.base.billing.ms.model.dao.Customer;
import com.base.billing.ms.model.dao.Item;
import com.base.billing.ms.model.dao.User;
import com.base.billing.ms.model.dto.BillDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

	void deleteByBillId(Long billId);

	@Transactional
	@Modifying
	@Query("""
			update Bill b set b.units = ?1, b.status = ?2, b.amount = ?3, b.updatedDate = ?4, b.item = ?5
			where b.billId = ?6""")
	int updateUnitsAndStatusAndAmountAndUpdatedDateAndItemByBillId(int units, String status, double amount, LocalDateTime updatedDate, Item item, Long billId);

	@Transactional
	@Modifying
	@Query("""
			insert into Bill (customer, units, amount, status, createdDate, item, user)
			values (?1, ?2, ?3, ?4, ?5, ?6, ?7)""")
	void createBill(Customer customer, int units, double amount, String status, LocalDateTime createdDate, Item item, User user);

	List<BillDto> findAllByCustomerAccountNumber(String accountNumber);

	List<BillDto> findAllByCustomer(Customer customer);

	List<BillDto> findAllByItem(Item item);

	List<BillDto> findAllByUser(User user);

	List<BillDto> findAllByStatus(String status);
}