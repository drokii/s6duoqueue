package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Tweet implements Serializable {


    private int id;
    private String message;
    private User author;
    private Date date;
    private int authorId;
    public Tweet(String message, User author) {
        this.message = message;
        this.author = author;
    }

    public Tweet() {
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
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 140)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Basic
    @Column(name = "author_id", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return id == tweet.id &&
                authorId == tweet.authorId &&
                Objects.equals(message, tweet.message) &&
                Objects.equals(date, tweet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, message, date);
    }
}
