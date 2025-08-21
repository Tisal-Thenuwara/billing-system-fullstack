package com.pahanaedu.service;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillingServiceTest {

	private BillingService billingService;
	private BillDAO billDAOMock;
	private CustomerDAO customerDAOMock;

	@BeforeEach
	void setUp() {
		billingService = new BillingService();
		billDAOMock = mock(BillDAO.class);
		customerDAOMock = mock(CustomerDAO.class);

		try {
			var billField = BillingService.class.getDeclaredField("billDAO");
			billField.setAccessible(true);
			billField.set(billingService, billDAOMock);

			var customerField = BillingService.class.getDeclaredField("customerDAO");
			customerField.setAccessible(true);
			customerField.set(billingService, customerDAOMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testSaveBill_Success() {
		Bill bill = new Bill();
		bill.setAccountNo(1);
		bill.setUnits(10);

		Customer customer = new Customer();
		customer.setAccountNo(1);
		customer.setUnitsConsumed(50);

		when(billDAOMock.createBill(bill)).thenReturn(true);
		when(customerDAOMock.get(1)).thenReturn(customer);
		when(customerDAOMock.updateUnits(1, 60)).thenReturn(true);

		boolean result = billingService.saveBill(bill);

		assertTrue(result);
		assertEquals(60, bill.getUnits()); // updated units
		verify(billDAOMock, times(1)).createBill(bill);
		verify(customerDAOMock, times(1)).get(1);
		verify(customerDAOMock, times(1)).updateUnits(1, 60);
	}

	@Test
	void testSaveBill_FailsIfBillNotCreated() {
		Bill bill = new Bill();
		bill.setAccountNo(1);
		bill.setUnits(10);

		when(billDAOMock.createBill(bill)).thenReturn(false);

		boolean result = billingService.saveBill(bill);

		assertFalse(result);
		verify(customerDAOMock, never()).get(anyInt());
	}

	@Test
	void testSaveBill_FailsIfCustomerNotFound() {
		Bill bill = new Bill();
		bill.setAccountNo(1);
		bill.setUnits(10);

		when(billDAOMock.createBill(bill)).thenReturn(true);
		when(customerDAOMock.get(1)).thenReturn(null);

		boolean result = billingService.saveBill(bill);

		assertFalse(result);
		verify(customerDAOMock, never()).updateUnits(anyInt(), anyInt());
	}
}
