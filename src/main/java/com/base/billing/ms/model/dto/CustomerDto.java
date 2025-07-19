package com.base.billing.ms.model.dto;

import com.base.billing.ms.model.dao.Customer;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Value
public class CustomerDto implements Serializable {
	String accountNumber;
	String name;
	String address;
	String phone;
	int unitsConsumed;
}