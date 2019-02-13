package dao;

import models.Trend;
import models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TrendDAO extends EntityDAO<Trend>{
    public TrendDAO() {
    }

    public List<Tweet> findAll() {
        Query query = em.createQuery("SELECT t FROM Trend t");
        return (List<Tweet>) query.getResultList();
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }

}
