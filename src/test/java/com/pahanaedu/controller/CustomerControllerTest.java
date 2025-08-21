package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

	private CustomerController customerController;
	private CustomerDAO customerDAOMock;

	@BeforeEach
	void setUp() {
		customerController = new CustomerController();
		customerDAOMock = mock(CustomerDAO.class);

		try {
			var field = CustomerController.class.getDeclaredField("dao");
			field.setAccessible(true);
			field.set(customerController, customerDAOMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testAddCustomerSuccess() {
		Customer customer = new Customer();
		when(customerDAOMock.add(customer)).thenReturn(true);

		Response response = customerController.add(customer);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(customer, response.getEntity());
	}

	@Test
	void testAddCustomerFailure() {
		Customer customer = new Customer();
		when(customerDAOMock.add(customer)).thenReturn(false);

		Response response = customerController.add(customer);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{error:Could not add customer}", response.getEntity());
	}

	@Test
	void testGetCustomerFound() {
		Customer customer = new Customer();
		when(customerDAOMock.get(1)).thenReturn(customer);

		Response response = customerController.get(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(customer, response.getEntity());
	}

	@Test
	void testGetCustomerNotFound() {
		when(customerDAOMock.get(1)).thenReturn(null);

		Response response = customerController.get(1);

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("{error:Not found}", response.getEntity());
	}

	@Test
	void testUpdateCustomerSuccess() {
		Customer customer = new Customer();
		when(customerDAOMock.update(customer)).thenReturn(true);

		Response response = customerController.update(1, customer);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(customer, response.getEntity());
		assertEquals(1, customer.getAccountNo());
	}

	@Test
	void testUpdateCustomerFailure() {
		Customer customer = new Customer();
		when(customerDAOMock.update(customer)).thenReturn(false);

		Response response = customerController.update(1, customer);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{error:Could not update customer}", response.getEntity());
	}

	@Test
	void testListCustomers() {
		List<Customer> customers = List.of(new Customer(), new Customer());
		when(customerDAOMock.listAll()).thenReturn(customers);

		List<Customer> result = customerController.list();

		assertEquals(customers, result);
	}

	@Test
	void testDeleteCustomerSuccess() {
		when(customerDAOMock.delete(1)).thenReturn(true);

		Response response = customerController.delete(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("{\"message\":\"Customer deleted successfully\"}", response.getEntity());
	}

	@Test
	void testDeleteCustomerFailure() {
		when(customerDAOMock.delete(1)).thenReturn(false);

		Response response = customerController.delete(1);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{\"error\":\"Could not delete customer\"}", response.getEntity());
	}
}
