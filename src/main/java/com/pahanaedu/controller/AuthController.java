package com.pahanaedu.controller;

import com.pahanaedu.service.AuthService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/auth")
public class AuthController {

    private final AuthService authService = new AuthService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Map<String, String> payload) {
        String username = payload.getOrDefault("username", "");
        String password = payload.getOrDefault("password", "");
        boolean ok = authService.validate(username, password);
        if (ok) {
            return Response.ok(Map.of("message","Login successful")).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(Map.of("error","Invalid credentials"))
                .build();
    }
}
