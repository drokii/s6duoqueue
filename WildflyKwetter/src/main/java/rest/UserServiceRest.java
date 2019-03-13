package rest;

import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Produces({"application/json"})
@Path("user")
public class UserServiceRest {

    @Inject
    UserService userService;

    @GET
    @Path("/getuser/{username}")
    public Response getUser(@PathParam("username") String username) {
        User user = userService.getUserByUsername(username);
        Gson gson = new Gson();
        String output = gson.toJson(user);
        return Response.status(200).entity(output).build();
    }


    @GET
    @Path("/getusers")
    public Response getAllUsers() {


        List<User> user = userService.getAllUsers();
        Gson gson = new Gson();
        String output = gson.toJson(user);
        return Response.status(200).entity(output).build();

    }

    @POST
    @Path("/edituser/{username}")
    public Response editUsername(@PathParam("username") String username, @FormParam("username") String desiredUsername) {
        try {
            userService.editName(username, desiredUsername);
            return Response.status(200).entity("Username has been changed!").build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (UsernameTakenException e) {
            return Response.status(200).entity("Username is already in use.").build();
        }

    }

    @POST
    @Path("/editprofile/{username}")
    public Response editUsername(@PathParam("username") String username, @FormParam("bio") String desiredBio, @FormParam("location") String desiredLocation, @FormParam("website") String desiredWebsite) {
        try {
            userService.editProfile(username, desiredBio, desiredLocation, desiredWebsite);
            return Response.status(200).entity("User details have been changed!").build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (MessageTooLongException e) {
            return Response.status(200).entity("Bio is too long.").build();
        }

    }

    @GET
    @Path("/editprofile/{username}/{followed}")
    public Response follow(@PathParam("username") String username, @PathParam("followed") String followed) {
        try {
            userService.follow(username, followed);
            return Response.status(200).entity("User details have been changed!").build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }


    }


}
