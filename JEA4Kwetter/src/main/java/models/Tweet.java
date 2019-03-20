package models;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Tweet {
    @Id
    @GeneratedValue
    private int id;

    private String message;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @OneToOne
    private User author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return id == tweet.id &&
                Objects.equals(message, tweet.message) &&
                Objects.equals(date, tweet.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message, date);
    }
}