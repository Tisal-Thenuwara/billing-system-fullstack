package com.base.billing.ms.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	private String accountNumber;

	private String name;
	private String address;
	private String phone;
	private int unitsConsumed;
}
