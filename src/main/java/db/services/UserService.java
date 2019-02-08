package db.services;

import db.DAOs.UserDAOImpl;
import models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAOImpl userDAO;

    public UserService() {
    }

    public void persist(User entity) {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.persist(entity);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public void update(User entity) {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.update(entity);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public User findById(int id) {
        userDAO.openCurrentSession();
        User user = userDAO.findById(id);
        userDAO.closeCurrentSession();
        return user;
    }

    public void delete(int id) {
        userDAO.openCurrentSessionwithTransaction();
        User book = userDAO.findById(id);
        userDAO.delete(book);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public List<User> findAll() {
        userDAO.openCurrentSession();
        List<User> books = userDAO.findAll();
        userDAO.closeCurrentSession();
        return books;
    }


    public UserDAOImpl bookDao() {
        return userDAO;
    }
}
