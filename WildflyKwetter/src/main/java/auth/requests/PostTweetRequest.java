package auth.requests;

import java.io.Serializable;

public class PostTweetRequest implements Serializable {

    private String username;
    private String message;

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
