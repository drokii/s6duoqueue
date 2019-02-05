package db.DAOs;

import db.interfaces.EntityDAO;
import models.User;

import java.util.List;

public class UserDAOImpl extends DataAccessClass implements EntityDAO<User, Integer> {

    @Override
    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public User findById(Integer id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        return (List<User>) getCurrentSession().createQuery("from User").list();
    }
}
