package com.base.billing.ms.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	private String accountNumber;

	private String firstName;

	private String lastName;

	private String addressLine1;

	private String addressLine2;

	private String addressLine3;

	private String city;

	private String postalCode;

	private String phone;

	private String email;

	private int unitsConsumed;
}
