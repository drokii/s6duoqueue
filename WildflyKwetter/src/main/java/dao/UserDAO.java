package dao;

import models.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAO extends EntityDAO<User>  {

    public UserDAO() {
    }

    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }



}
