package dao;

import dao.jpa.UserDAOJPA;
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

import java.util.ArrayList;

@RunWith(Arquillian.class)
public class UserDAOTest {
    private ArrayList<User> users;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, UserDAOJPA.class.getPackage(), User.class.getPackage())
                .addClass(GenericProducer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Before
    public void setUp(){
        users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("username" + i, "password" + i, "bio" +i, "website" + i ," location" + i));
        }

    }

    @Test
    public void findAll() {

    }

    @Test
    public void find() {
    }

    @Test
    public void findByUsername() {
    }
}
