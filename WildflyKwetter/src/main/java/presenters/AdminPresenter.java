package presenters;

import dao.jpa.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import models.Role;
import models.Tweet;
import models.User;
import services.TweetService;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Model
public class AdminPresenter {

    @Inject
    UserService userService;

    @Inject
    TweetService tweetService;

    @Inject
    UserDAOJPA userDAOJPA;

    @Produces
    @Named
    private String selectedUser;
    private List<User> users;
    private List<Tweet> tweets;

    public void onUserListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User member) {
        retrieveUsers();
    }

    public void onTweetListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Tweet member) throws UserNotFoundException {
        retrieveTweets();
    }

    private void retrieveUsers() {
        users = userService.getAllUsers();
    }

    private void retrieveTweets() throws UserNotFoundException {
        if(userService.getUserByUsername(selectedUser) != null){
            tweets = tweetService.getTweetsFromUser(selectedUser);
        }

    }

    @PostConstruct
    public void initNewProperty() {
        retrieveUsers();
    }

    public void clearUser(String username) throws UserNotFoundException {
        userService.deleteUser(username);
    }

    public void promote(String username) throws UserNotFoundException, IOException {
        userService.promoteUserToModerator(username);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void genTestdata() throws MessageTooLongException, UserNotFoundException {
        for (int i = 0; i < 10; i++) {
            User user = new User("username" + i, "password" + i, "bio" + i, "website" + i, " location" + i);
            user.setRole(Role.USER);
            userDAOJPA.create(user);
            System.out.println(user.getRole().toString());
            tweetService.postTweet("username"+i, "dab1");
        }
        User user = new User("test", "test" , "bio", "website", " location");
        user.setRole(Role.ADMIN);
        userDAOJPA.create(user);
        System.out.println(user.getRole().toString());
        tweetService.postTweet("test", "dab1");
    }

    public void debugRoles() {
        System.out.println(users.size());
        for (User u : users
        ) {
            System.out.println(u.getRole().toString());
        }
    }

    public void userChangedListener(ValueChangeEvent event) throws UserNotFoundException {
        System.out.println("Success");
        this.selectedUser = (String) event.getNewValue();
        retrieveTweets();
    }

    @Produces
    @Named
    public List<User> getUsers() {
        return users;
    }

    @Produces
    @Named
    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void deleteTweet(Tweet tweet) {
        tweetService.delete(tweet);
    }

    public String getUser() {
        return selectedUser;
    }

    public void setUser(String user) {
        this.selectedUser = user;
    }

    public void setUser(List<User> user) {
        this.users = users;
    }


}
