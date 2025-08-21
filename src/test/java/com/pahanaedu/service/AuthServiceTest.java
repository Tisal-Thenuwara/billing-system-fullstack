package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {

	private AuthService authService;
	private UserDAO userDAOMock;

	@BeforeEach
	void setUp() {
		authService = new AuthService();
		userDAOMock = mock(UserDAO.class);

		// Inject mock into AuthService using reflection
		try {
			var field = AuthService.class.getDeclaredField("userDAO");
			field.setAccessible(true);
			field.set(authService, userDAOMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testValidateReturnsTrueForValidUser() {
		User user = new User();
		user.setUsername("john");
		user.setPassword("secret");

		when(userDAOMock.findByUsername("john")).thenReturn(user);

		boolean result = authService.validate("john", "secret");

		assertTrue(result);
	}

	@Test
	void testValidateReturnsFalseForWrongPassword() {
		User user = new User();
		user.setUsername("john");
		user.setPassword("secret");

		when(userDAOMock.findByUsername("john")).thenReturn(user);

		boolean result = authService.validate("john", "wrong");

		assertFalse(result);
	}

	@Test
	void testValidateReturnsFalseWhenUserNotFound() {
		when(userDAOMock.findByUsername("unknown")).thenReturn(null);

		boolean result = authService.validate("unknown", "any");

		assertFalse(result);
	}
}
