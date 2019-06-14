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

    //test
    @Inject
    UserDAOJPA userDAOJPA;

    @Produces
    @Named
    User user;

    @Produces
    @Named
    Tweet tweet;

    private List<User> users;

    private List<Tweet> tweets;

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User member) {
        retrieveUsers();
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Tweet member) {
        retrieveTweets();
    }

    @Produces
    @Named
    public List<User> getUsers() {
        return users;
    }

    public void setUser(List<User> user) {
        this.users = users;
    }

    public void retrieveUsers() {
        users = userService.getAllUsers();
    }


    @PostConstruct
    public void initNewProperty() {
        retrieveUsers();
        retrieveTweets();
    }

    private void retrieveTweets() {
        tweets = tweetService.getAllTweets();
    }

    public void clear(String username) throws UserNotFoundException {
        userService.deleteUser(username);
    }

    public void promote(String username) throws UserNotFoundException, IOException {
        userService.promoteUserToModerator(username);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void genTestdata() throws MessageTooLongException, UserNotFoundException {

        User user = new User("test", "test", "bio" , "website" , " location" );
        user.setRole(Role.ADMIN);
        userDAOJPA.create(user);
        tweetService.postTweet("test", "This is a demo message.");
        System.out.println(user.getRole().toString());

        for (int i = 0; i < 10; i++) {
            User u = new User("username" + i, "password" + i, "bio" + i, "website" + i, " location" + i);
            u.setRole(Role.USER);
            userDAOJPA.create(u);
            userService.follow(u.getUsername(), "test");
            System.out.println(u.getRole().toString());
        }



    }
    public void debugRoles(){
        System.out.println(users.size());
        for (User u: users
             ) {

            System.out.println(u.getRole().toString());

        }
    }

    @Produces
    @Named
    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweet) {
        this.tweets = tweet;
    }

    public void clear(Tweet tweet) {
        tweetService.delete(tweet);
    }
}
