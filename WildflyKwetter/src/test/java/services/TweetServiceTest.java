package services;

import dao.EntityDAO;
import dao.TweetDAO;
import models.Role;
import models.Tweet;
import models.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import repo.GenericProducer;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TweetServiceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TweetService.class)
                .addClass(TweetDAO.class)
                .addClass(EntityDAO.class)
                .addClass(Tweet.class)
                .addClass(User.class)
                .addClass(Role.class)
                .addClass(GenericProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void postTweet() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void getTweetsFromUser() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void lookForTweet() {
        Assert.fail("Not yet implemented");
    }
}
