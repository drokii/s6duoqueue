package dao;

import models.Trend;
import models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TrendDAOJPA extends EntityDAOJPA<Trend> {
    public TrendDAOJPA() {
    }

    @Override
    public Trend find(int id) {
        return em.find(Trend.class, id);
    }

    @Override
    public List<Trend> findAll() {
        Query query = em.createQuery("SELECT t FROM Trend t");
        return (List<Trend>) query.getResultList();
    }


}
