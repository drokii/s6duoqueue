package db.services;

import db.DAOs.TweetDAOImpl;
import models.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TweetService {

    @Inject
    private TweetDAOImpl tweetDao;

    public TweetService() {
    }

    public void persist(Tweet entity) {
        tweetDao.openCurrentSessionwithTransaction();
        tweetDao.persist(entity);
        tweetDao.closeCurrentSessionwithTransaction();
    }

    public void update(Tweet entity) {
        tweetDao.openCurrentSessionwithTransaction();
        tweetDao.update(entity);
        tweetDao.closeCurrentSessionwithTransaction();
    }

    public Tweet findById(int id) {
        tweetDao.openCurrentSession();
        Tweet tweet = tweetDao.findById(id);
        tweetDao.closeCurrentSession();
        return tweet;
    }

    public void delete(int id) {
        tweetDao.openCurrentSessionwithTransaction();
        Tweet tweet = tweetDao.findById(id);
        tweetDao.delete(tweet);
        tweetDao.closeCurrentSessionwithTransaction();
    }

    public List<Tweet> findAll() {
        tweetDao.openCurrentSession();
        List<Tweet> tweets = tweetDao.findAll();
        tweetDao.closeCurrentSession();
        return tweets;
    }


    public TweetDAOImpl tweetDAO() {
        return tweetDao;
    }
}


