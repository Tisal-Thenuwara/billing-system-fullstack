package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}