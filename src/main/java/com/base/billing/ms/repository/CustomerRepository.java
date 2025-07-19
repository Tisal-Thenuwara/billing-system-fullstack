package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}