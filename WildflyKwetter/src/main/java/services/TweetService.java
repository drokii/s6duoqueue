package services;

import dao.TweetDAO;
import models.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class TweetService {
    @Inject
    TweetDAO tweetDAO;

    public String postTweet(int poster_id, String message) {

        if (message.length() > 160) {
            Date date = new Date();
            date.setTime(Calendar.getInstance().getTimeInMillis());
            Tweet tweet = new Tweet();
            tweet.setDate(date);
            tweet.setMessage(message);
            tweetDAO.create(tweet);
            return "Tweet posted!";
        } else {
            return "Tweet too long!";
        }

    }

    public List<Tweet> getTweetsFromUser(int id){
        //todo: organize tweets chronologically
        return tweetDAO.findAllbyUser(id);
    }

    public List<Tweet> lookForTweet(String search){
        List<Tweet> searchResult = new ArrayList<>();
        List<Tweet> tweets = tweetDAO.findAll();
        for (Tweet t :
                tweets) {
            if(t.getMessage().contains(search)){
                searchResult.add(t);
            }
        }
        return searchResult;
    }






}
