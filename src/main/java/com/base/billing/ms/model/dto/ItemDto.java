package com.base.billing.ms.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.base.billing.ms.model.dao.Item}
 */
@Value
public class ItemDto implements Serializable {
	String itemCode;
	String name;
	double pricePerUnit;
	int quantity;
}