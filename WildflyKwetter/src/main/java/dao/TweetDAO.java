package dao;

import models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TweetDAO extends EntityDAO<Tweet> {

    public TweetDAO() {
    }

    public List<Tweet> findAll() {
        List tweets = em.createQuery("SELECT t FROM Tweet t").getResultList();
        return tweets;
    }

    public List<Tweet> findAllbyUser(int id) {
        ArrayList<Tweet> tweets = new ArrayList();
        Query query = em.createQuery("SELECT t FROM Tweet t WHERE t.author_id = :id")
                .setParameter("id", id);
        return (List<Tweet>) query.getResultList();
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }

}
