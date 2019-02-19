package dao.jpa;

import dao.EntityDAO;
import exceptions.TweetNotFoundException;
import exceptions.UserNotFoundException;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TweetDAOJPATest {

    @Inject
    TweetDAOJPA tweetDAOJPA;


    @Before
    public void setUp(){
        for (int i = 0; i < 10; i++) {
            Tweet tweet = new Tweet();
            tweet.setMessage("Text" + i);
            tweetDAOJPA.create(tweet);
        }

    }
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, UserNotFoundException.class.getPackage(), TweetDAOJPA.class.getPackage(), User.class.getPackage())
                .addClasses(GenericProducer.class, EntityDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void find() throws TweetNotFoundException {
        assertTrue(tweetDAOJPA.find(1).getMessage().contains("Text"));

    }

    @Test(expected = TweetNotFoundException.class)
    public void findNonExisting() throws TweetNotFoundException {
        tweetDAOJPA.find(355353531);
    }

    @Test
    public void findAll() {
        assertTrue(tweetDAOJPA.findAll().size() > 0);
    }
}
