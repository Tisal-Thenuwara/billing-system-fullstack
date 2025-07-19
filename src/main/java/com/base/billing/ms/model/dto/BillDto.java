package com.base.billing.ms.model.dto;

import com.base.billing.ms.model.dao.Bill;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Bill}
 */
@Value
@Data
public class BillDto implements Serializable {
	Long billId;
	CustomerDto customer;
	int units;
	double amount;
	LocalDateTime createdDate;
	ItemDto item;
	UserDto user;
}