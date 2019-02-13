package dao;

import models.Tweet;


import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TweetDAO extends EntityDAO<Tweet> {

    public TweetDAO() {
    }

    public List<Tweet> findAll() {
        Query query = em.createQuery("SELECT t FROM Tweet t");
        return (List<Tweet>) query.getResultList();
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }
}
