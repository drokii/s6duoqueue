package presenters;

import dao.jpa.UserDAOJPA;
import exceptions.UserNotFoundException;
import models.Role;
import models.User;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Model
public class AdminPresenter {

    @Inject
    UserService userService;

    @Inject
    UserDAOJPA userDAOJPA;

    @Produces
    @Named
    User user;

    private List<User> users;

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User member) {
        retrieveUsers();
    }

    @Produces
    @Named
    public List<User> getUsers() {
        return users;
    }

    public void setUser(List<User> user) {
        this.users = users;
    }

    public void retrieveUsers() {
        users = userService.getAllUsers();
    }


    @PostConstruct
    public void initNewProperty() {
        retrieveUsers();
    }

    public void save() throws IOException, UserNotFoundException {
        userService.register(user);
    }

    public void clear(String username) throws UserNotFoundException {
        userService.deleteUser(username);
    }

    public void promote(String username) throws UserNotFoundException, IOException {
        userService.promoteUserToModerator(username);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void genTestdata() {
        for (int i = 0; i < 10; i++) {
            User user = new User("username" + i, "password" + i, "bio" + i, "website" + i, " location" + i);
            user.setRole(Role.USER);
            userDAOJPA.create(user);
            System.out.println(user.getRole().toString());
        }
    }
    public void debugRoles(){
        System.out.println(users.size());
        for (User u: users
             ) {

            System.out.println(u.getRole().toString());

        }
    }
}
