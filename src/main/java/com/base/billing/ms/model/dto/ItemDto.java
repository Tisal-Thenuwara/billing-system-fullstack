package com.base.billing.ms.model.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.base.billing.ms.model.dao.Item}
 */
@Value
@Data
public class ItemDto implements Serializable {
	String itemCode;
	String itemName;
	double pricePerUnit;
	int quantity;
	String description;
}