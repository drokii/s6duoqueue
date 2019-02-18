package services;

import dao.UserDAOJPA;
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

        if (!user.getUsername().contains(username)){
            throw new UserNotFoundException();
        }

        if (user.getUsername() != null && userDAO.findByUsername(desired).getUsername() == null) {
            user.setUsername(desired);
            userDAO.update(user);

        }else{
            throw new UsernameTakenException();
        }

    }

    public boolean editProfile(String username, String bio, String location, String website) throws UserNotFoundException {
        User user = new User();
        userDAO.findByUsername(username);

        if (user.getUsername() != null && bio.length() > 160) {
            user.setBio(bio);
            user.setLocation(location);
            user.setWebsite(website);
            userDAO.update(user);
            return true;
        }

        return false;
    }

    public boolean follow(String follower, String followed) throws UserNotFoundException {
        User userFollower = new User();
        User userFollowed = new User();

        userFollower = userDAO.findByUsername(follower);
        userFollowed = userDAO.findByUsername(followed);

        if (userFollowed.addFollower(userFollower)) {
            userDAO.update(userFollowed);
            userDAO.update(userFollower);
            return true;
        }

        return false;
    }

    public void logIn(){

    }

    public void logOut(){

    }

    public boolean register(User user) throws UserNotFoundException {

        if (userDAO.findByUsername(user.getUsername()) == null){
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
