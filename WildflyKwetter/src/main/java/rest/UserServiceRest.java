package rest;

import auth.Secured;
import auth.requests.EditUserRequest;
import auth.requests.FollowRequest;
import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Produces({"application/json"})
@Path("user")
public class UserServiceRest {

    @Inject
    UserService userService;

    @GET
    @Path("/{username}")
    public Response getUser(@PathParam("username") String username) {
        User user = userService.getUserByUsername(username);
        user.setPassword("");
        Gson gson = new Gson();
        String output = gson.toJson(user);
        return Response.status(200).entity(output).build();
    }

    @POST
    @Secured
    @Path("/edituser")
    public Response editUsername(EditUserRequest request) {
        try {
            userService.editName(request.getUsername(), request.getDesiredUsername());
            return Response.status(200).entity("Username has been changed!").build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (UsernameTakenException e) {
            return Response.status(200).entity("Username is already in use.").build();
        }

    }

    @POST
    @Path("/editprofile")
    public Response editProfile(EditUserRequest request) {
        try {
            userService.editProfile(request.getUsername(), request.getBio(), request.getLocation(), request.getWebsite());
            return Response.status(200).entity("User details have been changed!").build();
        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (MessageTooLongException e) {
            return Response.status(200).entity("Bio is too long.").build();
        }

    }

    @GET
    @Path("/follow")
    public Response follow(FollowRequest request) {
        try {
            userService.follow(request.getUsername(), request.getFollowed());
            return Response.status(200).entity("User followed!").build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }


    }


}
