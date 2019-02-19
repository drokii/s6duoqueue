package dao.jpa;

import dao.EntityDAO;
import dao.jpa.UserDAOJPA;
import exceptions.UserNotFoundException;
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
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

@RunWith(Arquillian.class)
public class UserDAOJPATest {

    @Inject
    UserDAOJPA userDAO;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, UserNotFoundException.class.getPackage(), UserDAOJPA.class.getPackage(), User.class.getPackage())
                .addClasses(GenericProducer.class, EntityDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Before
    public void setUp(){
        for (int i = 0; i < 10; i++) {
            User user = new User("username" + i, "password" + i, "bio" +i, "website" + i ," location" + i);
            userDAO.create(user);
        }

    }

    @Test
    public void findAll() {
        assertTrue(userDAO.findAll().size() > 0);
    }

    @Test
    public void find() throws UserNotFoundException {
        assertTrue(userDAO.find(1).getUsername().contains("username"));
    }

    @Test(expected = UserNotFoundException.class)
    public void findNonExisting() throws UserNotFoundException {
        userDAO.find(1433225235);
    }

    @Test
    public void findByUsername() throws UserNotFoundException {
        assertTrue(userDAO.findByUsername("username1").getUsername().contains("username1"));
    }

    @Test(expected = UserNotFoundException.class)
    public void findByUsernameNonExisting() throws UserNotFoundException {
        userDAO.findByUsername("useadarname1");
    }


}
