package dao;

import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext
    EntityManager em;

    public UserDAO() {
    }

    public boolean create(User u) {
        em.persist(u);
        return true;
    }

    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public void update(User u){
        em.merge(u);
    }

    public void delete(User user) {
        em.remove(user);
    }
}
