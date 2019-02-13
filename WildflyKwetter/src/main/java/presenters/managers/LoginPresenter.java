package presenters.managers;

import dao.UserDAO;
import models.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Model
public class LoginPresenter {
    @Inject
    UserDAO userDAO;

    @Produces
    @Named
    User user;

    @PostConstruct
    public void initNewProperty() {
        user = new User();
    }

    public String logIn() throws IOException {
        for (User u : userDAO.findAll()
                ) {
            if(u.getUsername() == user.getUsername() && u.getPassword() == user.getPassword()){
                FacesContext fContext = FacesContext.getCurrentInstance();
                ExternalContext extContext = fContext.getExternalContext();
                extContext.redirect("start.xhtml");
            }

        }
        return "Password and/or Username wrong. Try again.";
    }

    public void encryptString() {

    }

    public void register() throws IOException {
        userDAO.create(user);
        logIn();
    }


}
