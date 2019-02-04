package models;

import java.io.Serializable;

public class Mention implements Serializable {
    private Tweet originalTweet;
    private String mentioned;

    public Mention() {
    }

    public Tweet getOriginalTweet() {
        return originalTweet;
    }

    public void setOriginalTweet(Tweet originalTweet) {
        this.originalTweet = originalTweet;
    }

    public String getMentioned() {
        return mentioned;
    }

    public void setMentioned(String mentioned) {
        this.mentioned = mentioned;
    }

    public Mention(Tweet originalTweet, String mentioned) {
        this.originalTweet = originalTweet;
    }
}
