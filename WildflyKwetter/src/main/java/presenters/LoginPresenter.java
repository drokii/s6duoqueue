package presenters;

import presenters.helper.LogInRequest;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;


@Model
public class LoginPresenter {

    @Inject
    UserService userService;

    @Produces
    @Named
    LogInRequest loginRequest;


    @PostConstruct
    public void initNewProperty() {
        loginRequest = new LogInRequest();
    }

    public String login(LogInRequest request) throws IOException {
        System.out.println("logging in");

        if(userService.logIn(request.getUsername(),request.getPassword())){
            return "admin";
        }
        else return null;
    }
}