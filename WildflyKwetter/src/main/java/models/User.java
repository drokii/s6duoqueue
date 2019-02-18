package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String username;
    private String password;
    private String bio;
    private String website;
    private String location;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> followers= new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "followed",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> following = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "author")
    private List<Tweet> tweets= new ArrayList<>();

    public User() {
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public void addFollower(User u) {
        if (!followers.contains(u)) {
            followers.add(u);
            //TODO: UNFOLLOW MAYBE?
        }
    }

    public void addFollowing(User u) {
        if (!following.contains(u)) {
            following.add(u);
        }
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public User(String username, String password, String bio, String website, String location) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.website = website;
        this.location = location;
        this.role = Role.USER;
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }
}
