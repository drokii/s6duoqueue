package dao.jpa;

import exceptions.UserNotFoundException;
import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAOJPA extends EntityDAOJPA<User> {

    public UserDAOJPA() {
    }

    @Override
    @Transactional
    public List<User> findAll() {
        List users = em.createQuery("SELECT u FROM User u").getResultList();
        if (users.isEmpty()) {
            try {
                throw new UserNotFoundException();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }


    @Override
    @Transactional
    public User find(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public User findByUsername(String username) {

        List users = em.createQuery("SELECT u FROM User u WHERE u.username=:username")
                .setParameter("username", username)
                .getResultList();

        if (!users.isEmpty()) {
            return (User) users.get(0);
        }
        return null;
    }

    public List<Tweet> getAllTweetsFromUser(String username) throws UserNotFoundException {
        ArrayList<Tweet> tweets = new ArrayList();

        Query query = em.createQuery("SELECT t FROM Tweet t WHERE t.author = :id")
                .setParameter("id", findByUsername(username));
        return (List<Tweet>) query.getResultList();
    }
}
