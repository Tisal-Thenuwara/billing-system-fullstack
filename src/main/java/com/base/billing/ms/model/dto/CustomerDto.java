package com.base.billing.ms.model.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.base.billing.ms.model.dao.Customer}
 */
@Value
@Data
public class CustomerDto implements Serializable {
	String accountNumber;
	String firstName;
	String lastName;
	String addressLine1;
	String addressLine2;
	String addressLine3;
	String city;
	String postalCode;
	String phone;
	String email;
}