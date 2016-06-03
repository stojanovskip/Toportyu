package com.kerkyra.service;

import com.kerkyra.PasswordHasher;
import com.kerkyra.SessionManager;
import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by andras.timar on 6/2/2016.
 */
public class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    SessionManager sessionManager;
    private User user;
    @Mock
    PasswordHasher hasher;

    AuthenticationService authenticationService;
    private User user2;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(sessionManager,userRepository,hasher);
        user = new User();
        user.setName("Joe");
        user.setPassword("password");
        List<User> users = new ArrayList<User>();
        users.add(user);
        when(userRepository.findByName("Joe")).thenReturn(users);
        when(sessionManager.addUser(user)).thenReturn((long) 100);
        when(hasher.hash("password")).thenReturn("password");
        when(hasher.hash("notPassword")).thenReturn("notPassword");
        user2 = new User();
        when(sessionManager.addUser(user2)).thenReturn((long) 101);


    }

    @Test
    public void logIn_shouldReturnFalse_ifUserIsInDB_and_PasswordIsIncorrect() {
        user2.setName("Joe");
        user2.setPassword("notPassword");
        Assert.assertEquals(-1,authenticationService.login(user2));
    }

    @Test
    public void logIn_shouldReturnFalse_ifUserIsNotInDB() {
        user2.setName("Hal");
        user2.setPassword("password");
        Assert.assertEquals(-1,authenticationService.login(user2));
    }


    @Test
    public void logIn_shouldReturnTrue_ifUserIsInDB_and_PasswordIsCorrect() {
        Assert.assertEquals(100,authenticationService.login(user));
    }

    @Test
    public void should_Call_SessionManagersAddUserIfLoginDetailsAreCorrect(){
        user2.setName("Joe");
        user2.setPassword("password");

        authenticationService.login(user2);
        verify(sessionManager,times(1)).addUser(user2);
    }
    @Test
    public void should_not_Call_SessionManagersAddUserIfLoginDetailsAreNotCorrect(){
        user2.setName("Joe");
        user2.setPassword("notPassword");

        authenticationService.login(user2);
        verify(sessionManager,times(0)).addUser(user2);
    }

    @Test
    public void should_logOut_if_inSessionManager(){
        user2.setName("Joe");
        user2.setPassword("password");
        Assert.assertEquals(101,authenticationService.login(user2));
        authenticationService.logOut((long) 101);
        verify(sessionManager,times(1)).removeUser((long) 101);
    }

    @Test
    public void should_call_SessionManagerGetUsers(){
        List<User> users = authenticationService.getUsers();
        verify(sessionManager,times(1)).getUsers();
    }
    @Test
    public void should_call_SessionManagerGetUserWithGoodParameter(){
        authenticationService.getUser((long) 1000);
        verify(sessionManager,times(1)).getUser((long) 1000);
    }
    @Test
    public void should_registerIfNameAvailable(){
        User newUser = new User();
        newUser.setName("newName");
        when(userRepository.findByName(newUser.getName())).thenReturn(new ArrayList<User>());

        assertEquals(true,authenticationService.register(newUser));
    }
}