package dao;

import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserDAOTest {

    private static UserDAO userDAO;
    private static User testUser;


    @BeforeAll
    public static void setUp() {
        testUser = new User("username", "password", "bio", "website", "location");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(testUser);

        userDAO = new UserDAO();
        userDAO.em = mock(EntityManager.class);

        TypedQuery mockQuery = mock(TypedQuery.class);
        when(userDAO.em.createQuery(anyString())).thenReturn(mockQuery);
        when(userDAO.findAll()).thenReturn(userList);
        when(userDAO.em.find(any(Class.class), anyLong())).thenReturn(testUser);

    }

    @Test
    public void create() {
        Assertions.assertTrue(userDAO.create(testUser));
    }

    @Test
    void findAll() {
        Assertions.assertTrue(!userDAO.findAll().isEmpty());
    }

    @Test
    void find() {
        User user = userDAO.find((long) 1);
        Assertions.assertEquals(testUser, user);
    }

    @Test
    void update() {
        userDAO.create(testUser);
        testUser.setUsername("testchange");
        userDAO.update(testUser);
        Assertions.assertTrue("testchange".contains(userDAO.find((long) 1).getUsername()));

    }

    @Test
    void delete() {
        userDAO.create(testUser);
        Assertions.assertEquals(testUser, userDAO.find((long) 1));
        userDAO.delete(testUser);
    }
}