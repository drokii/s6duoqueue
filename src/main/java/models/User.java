package models;

import security.CharacterLimitExceededException;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


@Entity
public class User implements Serializable {
    @Id @GeneratedValue
    private String id;
    private String username;
    private String password;
    private String bio;
    private String location;
    private String website;
    private List<User> followers;
    private List<User> following;
    private List<Tweet> tweets;

    public User() {
    }

    public User(String username, String password, String bio, String location, String website, List<Tweet> tweets){
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.tweets = tweets;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}