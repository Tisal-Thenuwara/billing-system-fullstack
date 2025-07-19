package com.base.billing.ms.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	private String username;

	private String password;
}
