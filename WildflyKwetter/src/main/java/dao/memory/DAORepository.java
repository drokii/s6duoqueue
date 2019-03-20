package dao.memory;

import models.Tweet;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class DAORepository {

    private static List<User> users;
    private static List<Tweet> tweets;

    @PostConstruct
    public void init() throws IOException {
       users = new ArrayList<>();
       tweets = new ArrayList<>();
    }

    public static void clear(){
        users.clear();
        tweets.clear();
    }


    public List<User> getUsers() {
        return users;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

}
