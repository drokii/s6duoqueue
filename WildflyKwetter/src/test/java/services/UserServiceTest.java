package services;

import dao.EntityDAO;
import dao.jpa.EntityDAOJPA;
import dao.jpa.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.TweetNotFoundException;
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
    private User user2;
    @Inject
    UserService userService;

    @Inject
    UserDAOJPA userDAOJPA;


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(TweetNotFoundException.class, MessageTooLongException.class,  UserService.class, UsernameTakenException.class, EntityDAO.class,  EntityDAOJPA.class, UserDAOJPA.class, Tweet.class, User.class, Role.class, GenericProducer.class, UserNotFoundException.class)
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

        user2 = new User();
        user2.setWebsite("42");
        user2.setUsername("testuser3");
        user2.setLocation("testlocfsdf2");
        user2.setRole(Role.USER);
        user2.setWebsite("website 2");
        user2.setBio("yes3");
        userDAOJPA.create(user2);
    }

    @Test
    public void editName() throws UserNotFoundException, UsernameTakenException {
        userService.editName("testuser2", "yeah mane");
        assertTrue(userDAOJPA.findByUsername("yeah mane").getUsername().contains("yeah mane"));
        userService.editName("yeah mane","testuser2" );

    }
    @Test(expected = UserNotFoundException.class)
    public void editNameNonExistantUser() throws UserNotFoundException, UsernameTakenException {
        userService.editName("testusasdasdasdadasder2", "yeah mane");
    }

    @Test(expected = UsernameTakenException.class)
    public void editNameAlreadyInUse() throws UserNotFoundException, UsernameTakenException {
        userService.editName("testuser2", "testuser2");
    }

    @Test
    public void editProfile() throws UserNotFoundException, MessageTooLongException {
        userService.editProfile("testuser2","a different bio","a different location", "a different website");
        assertTrue(userDAOJPA.findByUsername("testuser2").getBio().contains("a different bio"));
        assertTrue(userDAOJPA.findByUsername("testuser2").getLocation().contains("a different location"));
        assertTrue(userDAOJPA.findByUsername("testuser2").getWebsite().contains("a different website"));

    }

    @Test(expected = UserNotFoundException.class)
    public void editProfileUserNotFound() throws UserNotFoundException, MessageTooLongException {
        userService.editProfile("testuser2adasdasdasdas","a different bio","a different location", "a different website");
    }
    @Test(expected = MessageTooLongException.class)
    public void editProfileBioTooLong() throws UserNotFoundException, MessageTooLongException {
        userService.editProfile("testuser2","a different biokajregpjepogjeropgepfpawejgporiejraeoigiopareneognioewjgnjo[aernjgioeJFIOAWEFKPOS;LFKPEASFJGUREGNTROGMEKAFNVRJITRAEMOIBPMAEORPGVMFADOVNTVNMAPEOFAKFJAOGJOJREOGIJAEROGIJAREIOGJAGIOJAREIOGJAREIOGJAEOIRJGIOAJI","a different location", "a different website");
    }

    @Test
    public void follow() throws UserNotFoundException {
        userService.follow("testuser2","testuser3");
        assertTrue(userDAOJPA.findByUsername("testuser2").getFollowing().size() > 0);
        assertTrue(userDAOJPA.findByUsername("testuser3").getFollowers().size() > 0);
    }

    @Test(expected = UserNotFoundException.class)
    public void followNonExistantUser() throws UserNotFoundException {
        userService.follow("testusersfdfsg2","testuser3");
        userService.follow("testuser2","asdsdafsdf");
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
    public void getAllUsers() throws UserNotFoundException {
        assertTrue(!userService.getAllUsers().isEmpty());
    }
}
