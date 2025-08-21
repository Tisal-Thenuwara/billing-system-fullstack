package com.pahanaedu.controller;

import com.pahanaedu.service.AuthService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthControllerTest {

	private AuthController authController;
	private AuthService authServiceMock;

	@BeforeEach
	void setUp() {
		// Use reflection or constructor injection (if refactored) to inject mock
		authController = new AuthController();
		authServiceMock = mock(AuthService.class);

		// Replace the real service with mock (via reflection hack)
		try {
			var field = AuthController.class.getDeclaredField("authService");
			field.setAccessible(true);
			field.set(authController, authServiceMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testLoginSuccess() {
		when(authServiceMock.validate("user", "pass")).thenReturn(true);

		Response response = authController.login(Map.of("username", "user", "password", "pass"));

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		Map<?, ?> entity = (Map<?, ?>) response.getEntity();
		assertEquals("Login successful", entity.get("message"));
	}

	@Test
	void testLoginFailure() {
		when(authServiceMock.validate("user", "wrong")).thenReturn(false);

		Response response = authController.login(Map.of("username", "user", "password", "wrong"));

		assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		Map<?, ?> entity = (Map<?, ?>) response.getEntity();
		assertEquals("Invalid credentials", entity.get("error"));
	}

	@Test
	void testLoginMissingFields() {
		when(authServiceMock.validate("", "")).thenReturn(false);

		Response response = authController.login(Map.of());

		assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		Map<?, ?> entity = (Map<?, ?>) response.getEntity();
		assertEquals("Invalid credentials", entity.get("error"));
	}
}
