package controllers;

import models.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Stateless
@Named
@Path("/user")
public class UserController {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public String logIn() {
        //todo: login
        userService.addUser(new User("username", "password", "bio", "website", "location"));
        System.out.println("dab");

        return "test";
    }


}
