package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}