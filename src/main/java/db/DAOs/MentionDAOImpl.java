package db.DAOs;

import db.interfaces.EntityDAO;
import models.Mention;
import models.Trend;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MentionDAOImpl extends DataAccessClass implements EntityDAO<Mention, Integer> {
    @Override
    public void persist(Mention entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Mention entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Mention findById(Integer id) {
        return getCurrentSession().get(Mention.class, id);
    }

    @Override
    public void delete(Mention entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        return (List<Mention>) getCurrentSession().createQuery("from Mention").list();
    }
}
