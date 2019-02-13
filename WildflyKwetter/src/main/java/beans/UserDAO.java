package beans;

import model.SimpleProperty;
import model.User;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
