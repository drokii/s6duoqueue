package rest;

import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import models.Tweet;
import services.TweetService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Produces({"application/json"})
@Consumes({"application/json"})
@Path("tweet")
public class TweetServiceRest {

    @Inject
    TweetService tweetService;

    @GET
    @Path("/gettweets/{username}")
    public Response getTweetsFrom(@PathParam("username") String username) {
        try {

            List<Tweet> tweetList = tweetService.getTweetsFromUser(username);
            Gson gson = new Gson();
            String output = gson.toJson(tweetList);
            return Response.status(200).entity(output).build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }

    }

    @POST
    @Path("/post")
    public Response postTweet(@FormParam("username") String username, @FormParam("message") String message) {
        try {
            tweetService.postTweet(username, message);
            return Response.status(200).entity("Tweet Posted!").build();
        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (MessageTooLongException e) {
            return Response.status(200).entity("Message is too long.").build();
        }
    }

    @GET
    @Path("/search/{search}")
    public Response postTweet(@PathParam("search") String search) {

        List<Tweet> tweetList = tweetService.lookForTweet(search);

        if (tweetList.isEmpty()) {
            return Response.status(200).entity("Nothing found containing " + search + "!").build();
        }

        Gson gson = new Gson();
        String output = gson.toJson(tweetList);

        return Response.status(200).entity(output).build();

    }

}



