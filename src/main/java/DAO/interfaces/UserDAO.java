package DAO.interfaces;

import models.User;

public interface UserDAO {

    void addUser(User user);
    User getUser(int id);
    void removeUser(int id);
    void updateUser(int id, String bio, String location, String website);

}
