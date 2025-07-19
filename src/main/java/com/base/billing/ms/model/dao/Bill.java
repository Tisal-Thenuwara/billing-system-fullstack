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

    @ManyToOne
    @JoinColumn(name = "customer_account_number")
    private Customer customer;

    private int units;

    private double amount;

	private String status;

    private LocalDateTime updatedDate;

    private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "item_code")
	private Item item;

    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;
}