package dao.memory;

import dao.EntityDAO;
import dao.jpa.TweetDAOJPA;
import exceptions.TweetNotFoundException;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserDAOMemoryTest {
    @Inject
    DAORepository daoRepository;

    @Inject
    UserDAOMemory userDAOMemory;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, UserNotFoundException.class.getPackage(), TweetDAOJPA.class.getPackage(), User.class.getPackage())
                .addClasses(UserDAOMemory.class, DAORepository.class, GenericProducer.class, EntityDAO.class, TweetDAOMemory.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp(){
        for (int i = 0; i < 10; i++) {
            User user = new User("username" + i, "password" + i, "bio" +i, "website" + i ," location" + i);
            userDAOMemory.create(user);
        }

    }
    @Test
    public void create() {
        userDAOMemory.create(new User());
        assertTrue(userDAOMemory.findAll().size() == 11);
        assertTrue(userDAOMemory.findAll().get(10).getId() == 10);
    }

    @Test
    public void delete() {

        while (userDAOMemory.findAll().size() > 1) {
            User user = userDAOMemory.findAll().get(userDAOMemory.findAll().size() - 1);
            userDAOMemory.delete(user);
        }

        assertTrue(userDAOMemory.findAll().size() == 1);

    }

    @Test
    public void update() {
        User user = new User();
        userDAOMemory.create(user);
        assertTrue(userDAOMemory.findAll().get((int) user.getId()).getId() == user.getId());
        User user2 = userDAOMemory.findAll().get((int) user.getId());
        user2.setUsername("test");
        assertTrue(userDAOMemory.findAll().get((int) user.getId()).getUsername().equals("test"));

    }

    @Test
    public void find() throws UserNotFoundException {
        assertTrue(userDAOMemory.find(2).getId() == 2);

    }
    @Test(expected = UserNotFoundException.class)
    public void findNonExisting() throws UserNotFoundException {
        userDAOMemory.find(2324);

    }

    @Test
    public void findAll() {
        assertTrue(!userDAOMemory.findAll().isEmpty());
    }

    @Test
    public void findByUsername() {
    }
}
