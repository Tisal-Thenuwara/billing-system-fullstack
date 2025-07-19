package com.base.billing.ms.model.dto;

import com.base.billing.ms.model.dao.Bill;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Bill}
 */
@Value
public class BillDto implements Serializable {
	Long billId;
	String accountNumber;
	LocalDateTime date;
	int units;
	double amount;
}