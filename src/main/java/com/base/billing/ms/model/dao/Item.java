package com.base.billing.ms.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {

	@Id
	private String itemCode;

	private String itemName;

	private double pricePerUnit;

	private int quantity;

	private String description;

	private String status;
}
