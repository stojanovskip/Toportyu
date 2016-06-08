package com.kerkyra.web;

import com.kerkyra.model.Credentials;
import com.kerkyra.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


/**
 * Created by Andras.Timar on 6/6/2016.
 */
public class AuthenticationControllerTest {

    @Mock
    AuthenticationService authenticationService;

    @Spy
    MockHttpServletResponse httpResponse;

    AuthenticationController controller;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(authenticationService.login("test","test")).thenReturn(Long.valueOf(100));
        when(authenticationService.login("nulltest","nulltest")).thenReturn(null);

        controller = new AuthenticationController(authenticationService);
    }

    @Test
    public void should_ReturnUserResponseWithNullName_AndDontAddCookie_IfLogInIsNull() {
        AuthenticationController.UserResponse response =
                controller.login(new Credentials("nulltest","nulltest"),httpResponse);
        assertEquals(null,response.username);
        verify(httpResponse,times(0)).addCookie(any(Cookie.class));
    }

    @Test
    public void should_ReturnUserResponseWithInputName_AndAddCookie_IfLogInIsNotNull() {
        AuthenticationController.UserResponse response =
                controller.login(new Credentials("test","test"),httpResponse);assertEquals("test",response.username);
        verify(httpResponse,times(1)).addCookie(any(Cookie.class));
    }
    @Test
    public void should_CallAuthServiceLogin(){
        AuthenticationController.UserResponse response =
                controller.login(new Credentials("test","test"),httpResponse);assertEquals("test",response.username);
        verify(authenticationService,times(1)).login("test","test");
    }
    @Test
    public void should_CallAuthServiceLogout_and_SetDeleteCookie_IfSessionIdNotNull(){
        Long testId = Long.valueOf(100);
        controller.logout(testId, httpResponse);
        verify(authenticationService,times(1)).logout(testId);
        verify(httpResponse,times(1)).addCookie(any(Cookie.class));

    }
    @Test
    public void should_CallAuthServiceGetUser_IfSessionIdNotNull(){
        Long testId = Long.valueOf(100);
        controller.getCurrentUser(testId);
        verify(authenticationService,times(1)).getUser(testId);
    }
    @Test
    public void should_not_CallAuthServiceGetUser_IfSessionIdNull(){
        Long testId = null;
        controller.getCurrentUser(testId);
        verify(authenticationService,times(0)).getUser(testId);
    }

}