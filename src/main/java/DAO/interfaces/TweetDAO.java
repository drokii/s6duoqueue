package DAO.interfaces;

import models.Tweet;
import models.User;

public interface TweetDAO {
    void addTweet(Tweet tweet);
    User getTweet(int id);
    void removeTweet(int id);
    void updateTweet(int id, String msg);
}
