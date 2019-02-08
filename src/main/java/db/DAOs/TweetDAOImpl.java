package db.DAOs;

import db.interfaces.EntityDAO;

import models.Tweet;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TweetDAOImpl extends DataAccessClass implements EntityDAO<Tweet, Integer> {
    @Override
    public void persist(Tweet entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Tweet entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Tweet findById(Integer id) {
        return getCurrentSession().get(Tweet.class, id);
    }

    @Override
    public void delete(Tweet entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        return (List<Tweet>) getCurrentSession().createQuery("from Tweet").list();
    }
}
