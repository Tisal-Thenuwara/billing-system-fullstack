package com.pahanaedu.controller;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    private final ItemDAO dao = new ItemDAO();

    @POST
    public Response add(Item it) {
        boolean ok = dao.add(it);
        if (ok) return Response.ok(it).build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{error:Could not add item}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Item it) {
        it.setItemId(id);
        boolean ok = dao.update(it);
        if (ok) return Response.ok(it).build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{error:Could not update item}")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean ok = dao.delete(id);
        if (ok) return Response.ok("{message:Deleted}").build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{error:Could not delete item}")
                .build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        Item it = dao.get(id);
        if (it == null) return Response.status(Response.Status.NOT_FOUND)
                .entity("{error:Not found}").build();
        return Response.ok(it).build();
    }

    @GET
    public List<Item> list() {
        return dao.listAll();
    }
}
