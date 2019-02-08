package db.DAOs;

import db.interfaces.EntityDAO;
import models.Trend;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TrendDAOImpl extends DataAccessClass implements EntityDAO<Trend, Integer> {

    @Override
    public void persist(Trend entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Trend entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Trend findById(Integer id) {
        return getCurrentSession().get(Trend.class, id);
    }

    @Override
    public void delete(Trend entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        return (List<Trend>) getCurrentSession().createQuery("from Trend").list();
    }
}

