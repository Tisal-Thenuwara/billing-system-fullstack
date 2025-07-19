package com.base.billing.ms.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.base.billing.ms.model.dao.User}
 */
@Value
public class UserDto implements Serializable {
	String username;
	String password;
}