package models;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Mention implements Serializable {

    private int id;
    private String mentioned;
    private int mentionedId;
    private int mentionerId;
    private int tweetId;

    public Mention() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMentioned() {
        return mentioned;
    }

    public void setMentioned(String mentioned) {
        this.mentioned = mentioned;
    }

    @Basic
    @Column(name = "mentioned_id", nullable = false)
    public int getMentionedId() {
        return mentionedId;
    }

    public void setMentionedId(int mentionedId) {
        this.mentionedId = mentionedId;
    }

    @Basic
    @Column(name = "mentioner_id", nullable = false)
    public int getMentionerId() {
        return mentionerId;
    }

    public void setMentionerId(int mentionerId) {
        this.mentionerId = mentionerId;
    }

    @Basic
    @Column(name = "tweet_id", nullable = false)
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mention mention = (Mention) o;
        return id == mention.id &&
                mentionedId == mention.mentionedId &&
                mentionerId == mention.mentionerId &&
                tweetId == mention.tweetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentionedId, mentionerId, tweetId);
    }
}
