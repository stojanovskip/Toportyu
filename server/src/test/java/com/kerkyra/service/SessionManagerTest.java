package com.kerkyra.service;

import com.kerkyra.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andras.Timar on 6/6/2016.
 */
public class SessionManagerTest {

    SessionManager sessionManager;

    private User user;
    private Long id;

    @Before
    public void init() {
        sessionManager = new SessionManager();
        user = new User();
        user.setUsername("test");
        id = sessionManager.setUser(user);
    }

    @Test
    public void shouldBeAbleToAddUser() {
        assertNotNull(sessionManager.getUser(id));
    }

    @Test
    public void should_ReturnCorrectUser() {
        User out = sessionManager.getUser(id);
        assertEquals(user.getUsername(), out.getUsername());
    }

    @Test
    public void shouldBeAbleTo_RemoveAddedUser() {
        sessionManager.removeUser(id);
        assertNull(sessionManager.getUser(id));
    }

}