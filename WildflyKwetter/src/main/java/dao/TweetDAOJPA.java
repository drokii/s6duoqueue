package dao;

import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TweetDAOJPA extends EntityDAOJPA<Tweet> {

    public TweetDAOJPA() {
    }

    @Override
    @Transactional
    public Tweet find(int id) {
        return em.find(Tweet.class, id);
    }

    @Override
    @Transactional
    public List<Tweet> findAll() {
        List tweets = em.createQuery("SELECT t FROM Tweet t").getResultList();
        return tweets;
    }



}
