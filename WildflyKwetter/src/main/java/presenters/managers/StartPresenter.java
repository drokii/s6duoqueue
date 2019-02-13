package presenters.managers;

import dao.MentionDAO;
import dao.TrendDAO;
import dao.TweetDAO;
import dao.UserDAO;
import models.Mention;
import models.Trend;
import models.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

public class StartPresenter {
    @Inject
    UserDAO userDAO;

    @Inject
    TweetDAO tweetDAO;

    @Inject
    MentionDAO mentionDao;

    @Inject
    TrendDAO trendDAO;


    @Produces
    @Named
    List<User> userList;

    @PostConstruct
    public void loadAllResources() {
        userList = userDAO.findAll();
        tweetList = tweetDAO.findAll();
    }


}
