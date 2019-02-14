package dao;

import exceptions.UserNotFoundException;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import models.User;

@Stateless
public class UserDAO extends EntityDAO<User> {

    public UserDAO() {
    }

    public List<User> findAll() throws UserNotFoundException {
        List users =  em.createQuery("SELECT u FROM User u").getResultList();
        if(users.isEmpty()){
            throw new UserNotFoundException();
        }
        return users;
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public User findByUsername(String username) throws UserNotFoundException {

        List users = em.createQuery("SELECT u FROM User u WHERE u.username=:username")
                .setParameter("username", username)
                .getResultList();
        return (User) users.get(0);

    }


}
