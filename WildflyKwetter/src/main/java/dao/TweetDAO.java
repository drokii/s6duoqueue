package dao;

import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TweetDAO extends EntityDAO<Tweet> {

    public TweetDAO() {
    }

    public List<Tweet> findAll(User user) {
        ArrayList<Tweet> tweets = new ArrayList();


        Query query = em.createQuery("SELECT t FROM Tweet t WHERE");
        return (List<Tweet>) query.getResultList();
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }
}
