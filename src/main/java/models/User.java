package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
public class User implements Serializable {
    private int id;
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

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "bio", nullable = false, length = 160)
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 40)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "website", nullable = false, length = 40)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(location, user.location) &&
                Objects.equals(website, user.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, bio, website, location);
    }
}