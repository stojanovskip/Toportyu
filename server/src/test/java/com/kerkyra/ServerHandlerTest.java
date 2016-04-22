
package com.kerkyra;

import com.kerkyra.httpserver.ServerHandler;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Madi.Yessirkepov on 4/13/2016.
 */

public class ServerHandlerTest {

    private HttpExchange httpExchange;
    private ServerHandler serverHandler;

    @Before
    public void setUp() {
        httpExchange = mock(HttpExchange.class);
        serverHandler = new ServerHandler(httpExchange);
    }

    @Test
    public void getRequestBodyTest() throws Exception {
        InputStream stream = new ByteArrayInputStream("testBody".getBytes(StandardCharsets.UTF_8));
        when(httpExchange.getRequestBody()).thenReturn(stream);
        String testMessage = serverHandler.getRequestBody();
        assertEquals("testBody", testMessage);
    }

    @Test
    public void respond() throws Exception {
        OutputStream outputStream = mock(OutputStream.class);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        serverHandler.respond(200, "testMessage");

        verify(httpExchange).sendResponseHeaders(200, "testMessage".length());
        verify(outputStream).write("testMessage".getBytes());
        verify(outputStream).close();
    }
}

