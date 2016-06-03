package com.kerkyra.service;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import com.kerkyra.SessionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 5/30/2016.
 */
public class SessionManagerTest {
    @Mock
    Random random;
    @InjectMocks
    SessionManager sessionManager;
    List<User> users = new ArrayList<User>();

    private User user;
    private Long id;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        user = new User();
        User user = this.user;
        user.setName("test");
        when(random.nextLong()).thenReturn((long) 1000);
        id = sessionManager.addUser(user);
    }

    @Test
    public void addUserGetUsers()
    {
        List<User> response = sessionManager.getUsers();
        Assert.assertEquals(user.getName(),response.get(0).getName());
    }

    @Test
    public void addUserGetUser()
    {
        User out = sessionManager.getUser((long) 1000);
        Assert.assertEquals("test",out.getName());
    }

    @Test
    public void removeUser()
    {
        sessionManager.removeUser(id);
        Assert.assertNull(sessionManager.getUser(id));
    }

}
