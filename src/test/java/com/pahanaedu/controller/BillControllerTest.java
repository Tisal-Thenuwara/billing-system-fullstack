package com.pahanaedu.controller;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillingService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BillControllerTest {

	private BillController billController;
	private BillDAO billDAOMock;
	private BillingService billingServiceMock;

	@BeforeEach
	void setUp() {
		billController = new BillController();
		billDAOMock = mock(BillDAO.class);
		billingServiceMock = mock(BillingService.class);

		// Inject mocks into private fields via reflection
		try {
			var billDAOField = BillController.class.getDeclaredField("billDAO");
			billDAOField.setAccessible(true);
			billDAOField.set(billController, billDAOMock);

			var billingServiceField = BillController.class.getDeclaredField("billingService");
			billingServiceField.setAccessible(true);
			billingServiceField.set(billController, billingServiceMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testCreateBillSuccess() {
		Bill bill = new Bill();
		when(billingServiceMock.saveBill(bill)).thenReturn(true);

		Response response = billController.createBill(bill);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		assertEquals(bill, response.getEntity());
	}

	@Test
	void testCreateBillFailure() {
		Bill bill = new Bill();
		when(billingServiceMock.saveBill(bill)).thenReturn(false);

		Response response = billController.createBill(bill);

		assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
		assertEquals("Failed to create bill.", response.getEntity());
	}

	@Test
	void testDeleteBillSuccess() {
		when(billDAOMock.deleteBill(1)).thenReturn(true);

		Response response = billController.deleteBill(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Bill deleted successfully.", response.getEntity());
	}

	@Test
	void testDeleteBillFailure() {
		when(billDAOMock.deleteBill(1)).thenReturn(false);

		Response response = billController.deleteBill(1);

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Bill not found or failed to delete.", response.getEntity());
	}

	@Test
	void testViewBillByIdFound() {
		Bill bill = new Bill();
		when(billDAOMock.viewBillById(1)).thenReturn(bill);

		Response response = billController.viewBillById(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(bill, response.getEntity());
	}

	@Test
	void testViewBillByIdNotFound() {
		when(billDAOMock.viewBillById(1)).thenReturn(null);

		Response response = billController.viewBillById(1);

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Bill not found with ID: 1", response.getEntity());
	}

	@Test
	void testViewAllBills() {
		List<Bill> bills = List.of(new Bill(), new Bill());
		when(billDAOMock.viewAllBills()).thenReturn(bills);

		Response response = billController.viewAllBills();

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(bills, response.getEntity());
	}
}
