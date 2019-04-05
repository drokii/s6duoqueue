package auth.requests;

import java.io.Serializable;

public class GetTweetsRequest implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

}
