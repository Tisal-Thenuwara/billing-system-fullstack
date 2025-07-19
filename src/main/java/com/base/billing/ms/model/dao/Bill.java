package com.base.billing.ms.model.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billId;

	private String accountNumber;

	private LocalDateTime date;
	private int units;
	private double amount;
}
