package com.pahanaedu.rest;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillingService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
public class BillingResource {

    private final BillingService billingService = new BillingService();

    @GET
    @Path("/{accountNo}")
    public Response bill(@PathParam("accountNo") int accountNo) {
        Bill bill = billingService.generateBill(accountNo);
        if (bill == null) return Response.status(Response.Status.NOT_FOUND)
                .entity("{"error":"Customer not found"}")
                .build();
        return Response.ok(bill).build();
    }
}
