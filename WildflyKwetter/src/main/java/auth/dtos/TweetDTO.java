package auth.dtos;

import java.io.Serializable;
import java.util.Date;

public class TweetDTO implements Serializable {

    String author;
    String message;
    Date date;

    public TweetDTO(String author, String message, Date date) {
        this.author = author;
        this.message = message;
        this.date = date;
    }
}
