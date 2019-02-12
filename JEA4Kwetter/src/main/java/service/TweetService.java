package service;

import dao.TweetDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TweetService {

    @Inject
    private TweetDAO tweetDAO;

    public TweetService() {

    }
}
