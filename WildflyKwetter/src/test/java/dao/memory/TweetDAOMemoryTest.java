package dao.memory;

import dao.EntityDAO;
import dao.jpa.TweetDAOJPA;
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

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class TweetDAOMemoryTest {

    @Inject
    TweetDAOMemory tweetDAOMemory;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, UserNotFoundException.class.getPackage(), TweetDAOJPA.class.getPackage(), User.class.getPackage())
                .addClasses(DAORepository.class, GenericProducer.class, EntityDAO.class, TweetDAOMemory.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 10; i++) {
            Tweet tweet = new Tweet();
            tweet.setMessage("Text" + i);
            tweetDAOMemory.create(tweet);
        }
    }

    @Test
    public void create() {
        tweetDAOMemory.create(new Tweet());
        assertTrue(tweetDAOMemory.findAll().size() == 11);
        assertTrue(tweetDAOMemory.findAll().get(10).getId() == 10);
    }

    @Test
    public void delete() {

        while (tweetDAOMemory.findAll().size() > 1) {
            Tweet tweet = tweetDAOMemory.findAll().get(tweetDAOMemory.findAll().size() - 1);
            tweetDAOMemory.delete(tweet);
        }

        assertTrue(tweetDAOMemory.findAll().size() == 1);

    }

    @Test
    public void update() {
        Tweet tweet = new Tweet();
        tweetDAOMemory.create(tweet);
        assertTrue(tweetDAOMemory.findAll().get(tweet.getId()).getId() == tweet.getId());
        Tweet tweet2 = tweetDAOMemory.findAll().get(tweet.getId());
        tweet2.setMessage("test");
        assertTrue(tweetDAOMemory.findAll().get(tweet.getId()).getMessage().equals("test"));

    }

    @Test
    public void find() throws TweetNotFoundException {
        assertTrue(tweetDAOMemory.find(2).getId() == 2);

    }
    @Test(expected = TweetNotFoundException.class)
    public void findNonExisting() throws TweetNotFoundException {
        tweetDAOMemory.find(2324);

    }

    @Test
    public void findAll() {
        assertTrue(!tweetDAOMemory.findAll().isEmpty());
    }

}
