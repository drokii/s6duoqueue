package models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String username;
    private String password;
    private String bio;
    private String website;
    private String location;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> followers;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "followed",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> following;
    @OneToMany
    private List<Tweet> tweets;


    public User(String username, String password, String bio, String website, String location) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.website = website;
        this.location = location;
    }

    public User() {
    }

    private void addFollower(User u) {
        if (!followers.contains(u)) {
            followers.add(u);
            u.addFollowed(this);
        }
    }

    private void addFollowed(User u) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(website, user.website) &&
                Objects.equals(location, user.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, bio, website, location);
    }
}
