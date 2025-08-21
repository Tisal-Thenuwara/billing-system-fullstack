package com.pahanaedu.controller;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillingService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/bills")
public class BillController {

	private final BillDAO billDAO = new BillDAO();
	private final BillingService billingService = new BillingService();

	// Create a new bill
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBill(Bill bill) {
		boolean isCreated = billingService.saveBill(bill);
		if (isCreated) {
			return Response.status(Response.Status.CREATED).entity(bill).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to create bill.").build();
		}
	}

	// Delete a bill by ID
	@DELETE
	@Path("/{billId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteBill(@PathParam("billId") int billId) {
		boolean isDeleted = billDAO.deleteBill(billId);
		if (isDeleted) {
			return Response.status(Response.Status.OK).entity("Bill deleted successfully.").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Bill not found or failed to delete.").build();
		}
	}

	// Get details of a single bill by ID
	@GET
	@Path("/{billId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewBillById(@PathParam("billId") int billId) {
		Bill bill = billDAO.viewBillById(billId);
		if (bill != null) {
			return Response.status(Response.Status.OK).entity(bill).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Bill not found with ID: " + billId).build();
		}
	}

	// Get all bills
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllBills() {
		List<Bill> bills = billDAO.viewAllBills();
		return Response.status(Response.Status.OK).entity(bills).build();
	}
}