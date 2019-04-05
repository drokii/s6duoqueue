package auth.requests;

import java.io.Serializable;

public class GetUserRequest implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

}
