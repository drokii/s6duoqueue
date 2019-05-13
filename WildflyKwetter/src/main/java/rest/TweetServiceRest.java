package rest;

import auth.Secured;
import auth.dtos.TweetDTO;
import auth.requests.PostTweetRequest;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import models.Tweet;
import models.User;
import services.TweetService;
import services.UserService;
import websockets.WebsocketEndpoint;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.server.ServerEndpointConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @Path("/get/{id}")
    public Response getTweetsFrom(@PathParam("id") long id) {
        try {

            List<Tweet> tweetList = tweetService.getTweetsFromUser(userService.getUserById((int) id).getUsername());

            Gson gson = new Gson();
            String output = gson.toJson(Lists.reverse(convertIntoDTO(tweetList)));
            return Response.status(200).entity(output).build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }

    }

    @GET
    @Path("/followers/{id}")
    public Response getTweetsFromFollowing(@PathParam("id") long id) {
        List<Tweet> allTweets = new ArrayList<>();
        List<Tweet> tweetList = null;

        try {

            for (User follower : userService.getUserById((int) id).getFollowing()) {
                tweetList = tweetService.getTweetsFromUser(follower.getUsername());
                allTweets.addAll(tweetList);
            }
            if (tweetList != null) {
                Collections.sort(tweetList, new Comparator<Tweet>() {
                    @Override
                    public int compare(Tweet t1, Tweet t2) {
                        return t1.getDate().compareTo((t2.getDate()));
                    }
                });
            }

            Gson gson = new Gson();
            String output = gson.toJson(Lists.reverse(convertIntoDTO(allTweets)));
            return Response.status(200).entity(output).build();

        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        }

    }

    @POST
    @Path("/post")
    public Response postTweet(PostTweetRequest request) {
        try {
            tweetService.postTweet(userService.getUserById(Integer.parseInt(request.getId())).getUsername(), request.getMessage());
            return Response.status(200).entity("Tweet Posted!").build();
        } catch (UserNotFoundException e) {
            return Response.status(200).entity("This user doesn't exist.").build();
        } catch (MessageTooLongException e) {
            return Response.status(200).entity("Message is too long.").build();
        }
    }

    @GET
    @Path("/search/{search}")
    public Response searchTweet(@PathParam("search") String search) {

        List<Tweet> tweetList = tweetService.lookForTweet(search);

        if (tweetList.isEmpty()) {
            return Response.status(200).entity("").build();
        }

        Gson gson = new Gson();
        String output = gson.toJson(convertIntoDTO(tweetList));

        return Response.status(200).entity(output).build();

    }

    private List<TweetDTO> convertIntoDTO(List<Tweet> tweets) {
        List<TweetDTO> dataObjectList = new ArrayList<>();

        for (Tweet t :
                tweets) {
            dataObjectList.add(new TweetDTO(t.getAuthor().getUsername(), t.getMessage(), t.getDate(), String.valueOf((t.getAuthor().getId()))));
        }

        return dataObjectList;
    }

}



