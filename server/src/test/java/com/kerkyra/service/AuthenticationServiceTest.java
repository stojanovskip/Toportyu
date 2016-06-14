package com.kerkyra.service;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 6/6/2016.
 */
public class AuthenticationServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    SessionManager sessionManager;
    @Mock
    PasswordHasher hasher;

    private User user;

    AuthenticationService authenticationService;
    private String password = "password";
    private String notPassword = "notPassword";
    private String goodUserName = "Joe";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(sessionManager, userRepository, hasher);
        user = new User();
        user.setUsername(goodUserName);
        user.setHashedPassword(password);
        List<User> users = new ArrayList<User>();
        users.add(user);
        when(userRepository.findByUsername(goodUserName)).thenReturn(users);
        when(sessionManager.setUser(user)).thenReturn(Long.valueOf(100));
        when(hasher.hash(password)).thenReturn(password);
        when(hasher.hash(notPassword)).thenReturn(notPassword);
    }

    @Test
    public void logIn_shouldReturnNull_IfUserIsNotInDB() {
        assertEquals(null, authenticationService.login("NotJoe", password));
    }

    @Test
    public void logIn_shouldReturnNull_IfUserIsInDB_and_PasswordIsIncorrect() {
        assertEquals(null, authenticationService.login(goodUserName, notPassword));
    }

    @Test
    public void logIn_shouldReturnSessionID_IfUserIsInDB_and_PasswordIsCorrect() {
        assertEquals(Long.valueOf(100), authenticationService.login(goodUserName, password));
    }

    @Test
    public void should_Call_SessionManagersAddUser_IfLoginDetailsAreCorrect() {
        authenticationService.login(goodUserName, password);
        verify(sessionManager, times(1)).setUser(user);
    }

    @Test
    public void should_not_Call_SessionManagersAddUser_IfLoginDetailsAreNotCorrect() {
        User user2 = new User();
        user2.setUsername(goodUserName);
        user2.setHashedPassword(notPassword);

        authenticationService.login(user2.getUsername(), user2.getHashedPassword());
        verify(sessionManager, times(0)).setUser(user2);
    }

    @Test
    public void should_call_SessionManager_removeUserWithGoodParameter() {
        authenticationService.logout((long) 1000);
        verify(sessionManager, times(1)).removeUser((long) 1000);
    }

    @Test
    public void should_call_SessionManagerGetUserWithGoodParameter() {
        authenticationService.getUser((long) 1000);
        verify(sessionManager, times(1)).getUser((long) 1000);
    }


}