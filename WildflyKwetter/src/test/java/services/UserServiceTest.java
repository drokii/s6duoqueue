package services;

import dao.EntityDAO;
import dao.EntityDAOJPA;
import dao.TweetDAOJPA;
import dao.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
import exceptions.UsernameTakenException;
import models.Role;
import models.Tweet;
import models.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repo.GenericProducer;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceTest {

    private User user;

    @Inject
    UserService userService;

    @Inject
    UserDAOJPA userDAOJPA;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(UserService.class, UsernameTakenException.class, EntityDAO.class,  EntityDAOJPA.class, UserDAOJPA.class, Tweet.class, User.class, Role.class, GenericProducer.class, UserNotFoundException.class)
                .addPackages(true, "org.hibernate")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() {
        // Set up test user
        user = new User();
        user.setWebsite("2");
        user.setUsername("testuser2");
        user.setLocation("testloc2");
        user.setRole(Role.USER);
        user.setWebsite("website");
        user.setBio("yes2");
        userDAOJPA.create(user);
    }

    @Test
    public void editName() throws UserNotFoundException, UsernameTakenException {
        userService.editName("testuser2", "yeah mane");
        assertTrue(userDAOJPA.findByUsername("yeah mane").getUsername().contains("yeah mane"));

    }

    @Test
    public void editProfile() {
    }

    @Test
    public void follow() {
    }

    @Test
    public void logIn() {
    }

    @Test
    public void logOut() {
    }

    @Test
    public void register() {
    }

    @Test
    public void promoteUserToModerator() {
    }

    @Test
    public void getAllUsers() {
    }
}
