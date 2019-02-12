package dao;

import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TweetDAO {

    @PersistenceContext
    private EntityManager em;

    public TweetDAO() {
    }

    public boolean create(Tweet t) {
        em.persist(t);
        return true;
    }

    public List<Tweet> findAll() {
        Query query = em.createQuery("SELECT t FROM Tweet t");
        return (List<Tweet>) query.getResultList();
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }
}
