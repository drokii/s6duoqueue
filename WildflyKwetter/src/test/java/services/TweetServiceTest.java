package services;

import dao.EntityDAO;
import dao.jpa.EntityDAOJPA;
import dao.jpa.TweetDAOJPA;
import dao.jpa.UserDAOJPA;
import exceptions.MessageTooLongException;
import exceptions.UserNotFoundException;
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

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class TweetServiceTest {
    @Inject
    UserDAOJPA userDAO;

    @Inject
    TweetService tweetService;

    @Inject
    TweetDAOJPA tweetDAO;

    User user;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(MessageTooLongException.class, EntityDAO.class, TweetService.class, TweetDAOJPA.class, EntityDAOJPA.class, UserDAOJPA.class, Tweet.class, User.class, Role.class, GenericProducer.class, UserNotFoundException.class)
                .addPackages(true, "org.hibernate")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() {
        // Set up test user
        user = new User();
        user.setWebsite("1");
        user.setUsername("testuser");
        user.setLocation("testloc");
        user.setRole(Role.USER);
        user.setWebsite("website");
        user.setBio("yes");
        userDAO.create(user);

    }


    @Test
    public void getTweetsFromUser() throws MessageTooLongException, UserNotFoundException {
        tweetService.postTweet("testuser", "dab1");
        tweetService.postTweet("testuser", "dab2");
        tweetService.postTweet("testuser", "dab3");
        tweetService.postTweet("testuser", "dab4");
        System.out.println(tweetService.getTweetsFromUser("testuser").size());
        assertTrue(tweetService.getTweetsFromUser("testuser").size() >= 4);
    }

    @Test
    public void postTweet() throws UserNotFoundException, MessageTooLongException {

        tweetService.postTweet("testuser", "x");
        assertTrue(tweetService.lookForTweet("x").size() ==1);
    }

    @Test(expected = MessageTooLongException.class)
    public void postTweetFailureTooLong() throws MessageTooLongException, UserNotFoundException {

        tweetService.postTweet("testuser", "dabpfjewpofjewpogjeorapjgpoarejgopawejfopeawjfopawjefopawejfpoajwepfoawefjaopewfjoapwfjawpoefjwepofjawpoefjapowefjopafjpoweafopwaefjopawefjopwaefjopwaefjaowpfjwaopfjowaepjfowaepfjoawefjopawjfpowjfowapfjowpfjawopfjwoapfjaowpfjwoafjoapwkagpoagjpoerajgoprejgpoaerjgoparejgvpioaerjgpioj");

    }

    @Test(expected = UserNotFoundException.class)
    public void postTweetFailureUserNonexisting() throws MessageTooLongException, UserNotFoundException {
        tweetService.postTweet("doesntexist", "dab");

    }


    @Test(expected = UserNotFoundException.class)
    public void getTweetsFromNonexistingUser() throws MessageTooLongException, UserNotFoundException {
        tweetService.getTweetsFromUser("dasdasd");

    }

    @Test
    public void lookForTweet() throws MessageTooLongException, UserNotFoundException {
        tweetService.postTweet("testuser", "look for this");
        tweetService.postTweet("testuser", "this is something to look for");

        assertTrue(tweetService.lookForTweet("this").size() == 2);
    }
}
