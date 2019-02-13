package presenters.managers;

import dao.UserDAO;
import models.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class UserManager {
    @Inject
    UserDAO userDAO;

    @Produces
    @Named
    User user;

    @Inject
    UserProducer producer;

    @PostConstruct
    public void initNewUser() {
        user = new User();
    }

    public void save() {
        userDAO.create(user);
        initNewUser();
    }

    public void clear(User user) {
        userDAO.delete(user);

    }
}
