package services;

import dao.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.Role;
import models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    UserDAOJPA userDAO;


    public void editName(String username, String desired) throws UserNotFoundException, UsernameTakenException {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (userDAO.findByUsername(desired) == null) {
            user.setUsername(desired);
            userDAO.update(user);

        } else {
            throw new UsernameTakenException();
        }

    }

    public void editProfile(String username, String bio, String location, String website) throws UserNotFoundException, MessageTooLongException {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (bio.length() < 160) {
            user.setBio(bio);
            user.setLocation(location);
            user.setWebsite(website);
            userDAO.update(user);

        } else {
            throw new MessageTooLongException();
        }

    }

    public void follow(String follower, String followed) throws UserNotFoundException {

        User userFollower = userDAO.findByUsername(follower);
        User userFollowed = userDAO.findByUsername(followed);

        if(userFollowed == null || userFollower == null){
            throw new UserNotFoundException();
        }

        userFollower.addFollowing(userFollowed);
        userFollowed.addFollower(userFollower);

        userDAO.update(userFollowed);
        userDAO.update(userFollower);

    }

    public void logIn() {

    }

    public void logOut() {

    }

    public boolean register(User user) throws UserNotFoundException {

        if (userDAO.findByUsername(user.getUsername()) == null) {
            User u = user;
            u.setRole(Role.USER);
            userDAO.create(user);

            return true;
        }
        return false;
    }

    public boolean promoteUserToModerator(String username) throws UserNotFoundException {
        User user = new User();
        userDAO.findByUsername(username);

        if (user.getUsername() != null) {

            user.setRole(Role.MOD);
            userDAO.update(user);
            return true;
        }

        return false;
    }

    public List<User> getAllUsers() throws UserNotFoundException {
        return userDAO.findAll();
    }

}
