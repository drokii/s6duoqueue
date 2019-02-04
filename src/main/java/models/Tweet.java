package models;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {
    private String message;
    private User author;
    private Date date;

    public Tweet(String message, User author) {
        this.message = message;
        this.author = author;
    }

    public Tweet() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
