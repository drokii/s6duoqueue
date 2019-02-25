package rest;

import dao.EntityDAO;
import dao.jpa.EntityDAOJPA;
import dao.jpa.TweetDAOJPA;
import dao.jpa.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.TweetNotFoundException;
import exceptions.UserNotFoundException;
import models.Role;
import models.Tweet;
import models.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repo.GenericProducer;
import services.TweetService;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TweetServiceRestTest {

    User user;

    @Inject
    TweetServiceRest tweetServiceRest;

    @Inject
    UserDAOJPA userDAO;

    @Inject
    TweetService tweetService;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(EntityDAO.class, TweetService.class, TweetDAOJPA.class, EntityDAOJPA.class, UserDAOJPA.class, Tweet.class, User.class, Role.class, GenericProducer.class)
                .addPackages(true, TweetNotFoundException.class.getPackage(), TweetServiceRest.class.getPackage())
                .addPackage("org.hibernate")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws MessageTooLongException, UserNotFoundException {
        user = new User();
        user.setWebsite("1");
        user.setUsername("testuser");
        user.setLocation("testloc");
        user.setRole(Role.USER);
        user.setWebsite("website");
        user.setBio("yes");
        userDAO.create(user);
        tweetService.postTweet("testuser", "dab1");
        tweetService.postTweet("testuser", "dab2");
}

    @Test
    @RunAsClient
    public void getTweetsFrom(@ArquillianResource final WebTarget webTarget) {

        final Response response = webTarget
                .path("/gettweets/testuser")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(true, response.readEntity(List.class));
    }

    @Test
    public void postTweet() {
    }

    @Test
    public void postTweet1() {
    }
}
