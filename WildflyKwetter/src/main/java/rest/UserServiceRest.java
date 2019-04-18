package rest;

import auth.Secured;
import auth.dtos.TweetDTO;
import auth.dtos.UserDTO;
import auth.requests.EditUserRequest;
import auth.requests.FollowRequest;
import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.Tweet;
import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Produces({"application/json"})
@Path("user")
public class UserServiceRest {

    @Inject
    UserService userService;
    // TODO: GETTERS USING USERNAME NEED TO SHIFT TO ID
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) throws UserNotFoundException {
        User user = userService.getUserById(Integer.parseInt(id));

        Gson gson = new Gson();
        String output = gson.toJson(new UserDTO(user.getBio(), user.getWebsite(), user.getLocation(), user.getUsername()));
        return Response.status(200).entity(output).build();
    }

    @POST
    @Secured
    @Path("/edituser")
    public Response editUsername(EditUserRequest request) throws MessageTooLongException, UserNotFoundException {
        try {
            userService.editName(request.getUsername(), request.getDesiredUsername());
            return Response.status(200).entity("Profile and Username has been changed!").build();
        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (UsernameTakenException e) {
            userService.editProfile(request.getUsername(), request.getBio(), request.getLocation(), request.getWebsite());
            return Response.status(200).entity("Profile Edited.").build();
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

    private List<UserDTO> convertIntoDTO(List<User> users){
        List<UserDTO> dataObjectList = new ArrayList<>();

        for (User user :
                users) {
            dataObjectList.add(new UserDTO(user.getBio(), user.getWebsite(), user.getLocation(), user.getUsername()));
        }

        return dataObjectList;
    }


}
