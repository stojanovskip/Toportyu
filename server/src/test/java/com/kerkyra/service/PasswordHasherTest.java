package com.kerkyra.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Andras.Timar on 6/6/2016.
 */
public class PasswordHasherTest {

    PasswordHasher passwordHasher;

    private String testpass;

    @Before
    public void init() {
        testpass = "testpass";
        passwordHasher = new PasswordHasher();
    }

    @Test
    public void sameInput_Shoud_HashToSameResult() {
        String testpassCopy = "testpass";
        assertEquals(passwordHasher.hash(testpass), passwordHasher.hash(testpassCopy));
    }

    @Test
    public void differenctInput_ShoudNot_HashToSameResult() {
        String otherTestpass = "notTestPass";
        assertNotEquals(passwordHasher.hash(testpass), passwordHasher.hash(otherTestpass));
    }
}