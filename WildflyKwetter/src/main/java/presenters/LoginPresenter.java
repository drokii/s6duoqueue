package presenters;

import models.Role;
import presenters.helper.LogInCallbackHandler;
import services.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;


@Model
public class LoginPresenter {

    @Inject
    UserService userService;
    private String username;
    private String password;

    public LoginPresenter() {
    }


    public String login() throws IOException {
        try {
            LoginContext lc = new LoginContext("jboss-security-api", new LogInCallbackHandler(username, password));
            lc.login();
            System.out.println(lc.getSubject().getPrincipals().toString());
            if(userService.getUserByUsername(username).getRole() == Role.ADMIN){
                return "admin";
            }
            else{
                //todo add ui message evt.
                System.out.println("user not an admin");
                return null;
            }
        } catch (LoginException e) {
            e.printStackTrace();
            System.out.println("Wrong Password");
            return null;
        } catch(SecurityException e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    public Object getUsername() {
        return username;
    }

    public Object getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}