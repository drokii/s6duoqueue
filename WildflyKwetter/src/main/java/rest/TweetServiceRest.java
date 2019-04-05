package rest;

import auth.Secured;
import auth.requests.GetTweetsRequest;
import auth.requests.PostTweetRequest;
import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import models.Tweet;
import models.User;
import services.TweetService;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Produces({"application/json"})
@Consumes({"application/json"})
@Secured
@Path("tweet")
public class TweetServiceRest {

    @Inject
    TweetService tweetService;

    @Inject
    UserService userService;

    @GET
    @Path("/from")
    public Response getTweetsFrom(GetTweetsRequest request) {
        try {

            List<Tweet> tweetList = tweetService.getTweetsFromUser(request.getUsername());
            Gson gson = new Gson();
            String output = gson.toJson(tweetList);
            return Response.status(200).entity(output).build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }

    }

    @GET
    @Path("/followers/{username}")
    public Response getTweetsFromFollowers(GetTweetsRequest request) {
        List<Tweet> allTweets = new ArrayList<>();
        List<Tweet> tweetList;
        try {

            for (User follower: userService.getUserByUsername(request.getUsername()).getFollowers()){
               tweetList = tweetService.getTweetsFromUser(follower.getUsername());
               allTweets.addAll(tweetList);
            }

            Gson gson = new Gson();
            String output = gson.toJson(allTweets);
            return Response.status(200).entity(output).build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }

    }

    @POST
    @Path("/post")
    public Response postTweet(PostTweetRequest request) {
        try {
            tweetService.postTweet(request.getUsername(), request.getMessage());
            return Response.status(200).entity("Tweet Posted!").build();
        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (MessageTooLongException e) {
            return Response.status(200).entity("Message is too long.").build();
        }
    }

    @GET
    @Path("/{search}")
    public Response searchTweet(@PathParam("search") String search) {

        List<Tweet> tweetList = tweetService.lookForTweet(search);

        if (tweetList.isEmpty()) {
            return Response.status(200).entity("Nothing found containing " + search + "!").build();
        }

        Gson gson = new Gson();
        String output = gson.toJson(tweetList);

        return Response.status(200).entity(output).build();

    }

}



