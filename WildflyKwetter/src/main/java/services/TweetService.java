package services;

import dao.TweetDAOJPA;
import dao.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class TweetService {
    @Inject
    TweetDAOJPA tweetDAO;
    @Inject
    UserDAOJPA userDAOJPA;

    public void postTweet(String username, String message) throws UserNotFoundException, MessageTooLongException {
        User user = userDAOJPA.findByUsername(username);

        if(user == null){
            throw new UserNotFoundException();
        }

        if (message.length() < 160) {

            Date date = new Date();
            date.setTime(Calendar.getInstance().getTimeInMillis());

            Tweet tweet = new Tweet();
            tweet.setDate(date);
            tweet.setMessage(message);

            tweet.setAuthor(user);
            user.addTweet(tweet);

            tweetDAO.update(tweet);
            userDAOJPA.update(user);

        } else {
            throw new MessageTooLongException();
        }


    }

    public List<Tweet> getTweetsFromUser(String username) throws UserNotFoundException {
       if(userDAOJPA.findByUsername(username) == null){
           throw new UserNotFoundException();
       }
        return userDAOJPA.getAllTweetsFromUser(username);
    }

    public List<Tweet> lookForTweet(String search) {
        List<Tweet> searchResult = new ArrayList<>();
        List<Tweet> tweets = tweetDAO.findAll();
        for (Tweet t :
                tweets) {
            if (t.getMessage().contains(search)) {
                searchResult.add(t);
            }
        }
        return searchResult;
    }


}
