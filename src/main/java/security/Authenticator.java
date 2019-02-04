package security;

public class Authenticator {

    public String authenticate(String username, String password) {
        if (("test".equalsIgnoreCase(username))
                && ("password".equals(password))) {
            return "success";
        } else {
            return "failure";
        }
    }
}