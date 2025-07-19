package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	Item findByItemCode(String itemCode);

	@Transactional
	@Modifying
	@Query("update Item i set i.status = ?1 where i.itemCode = ?2")
	int updateStatusByItemCode(String status, String itemCode);

	@Transactional
	@Modifying
	@Query("update Item i set i.itemName = ?1, i.description = ?2 where i.itemCode = ?3")
	int updateItemNameAndDescriptionByItemCode(String itemName, String description, String itemCode);

	@Transactional
	@Modifying
	@Query("update Item i set i.pricePerUnit = ?1, i.quantity = ?2 where i.itemCode = ?3")
	int updatePricePerUnitAndQuantityByItemCode(double pricePerUnit, int quantity, String itemCode);

	List<Item> findByStatus(String status);
}