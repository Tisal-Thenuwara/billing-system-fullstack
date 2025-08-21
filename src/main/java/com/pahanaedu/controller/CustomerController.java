package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private final CustomerDAO dao = new CustomerDAO();

    @Path("/save")
    @POST
    public Response add(Customer c) {
        boolean ok = dao.add(c);
        if (ok) return Response.ok(c).build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{error:Could not add customer}")
                .build();
    }

    @GET
    @Path("/{accountNo}")
    public Response get(@PathParam("accountNo") int accountNo) {
        Customer c = dao.get(accountNo);
        if (c == null) return Response.status(Response.Status.NOT_FOUND)
                .entity("{error:Not found}")
                .build();
        return Response.ok(c).build();
    }

    @PUT
    @Path("/{accountNo}")
    public Response update(@PathParam("accountNo") int accountNo, Customer c) {
        c.setAccountNo(accountNo);
        boolean ok = dao.update(c);
        if (ok) return Response.ok(c).build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{error:Could not update customer}")
                .build();
    }

    @GET
    public List<Customer> list() {
        return dao.listAll();
    }

    @DELETE
    @Path("/{accountNo}")
    public Response delete(@PathParam("accountNo") int accountNo) {
        boolean ok = dao.delete(accountNo);
        if (ok) {
            return Response.ok("{\"message\":\"Customer deleted successfully\"}").build();
        } 
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\":\"Could not delete customer\"}")
                .build();
    }
}