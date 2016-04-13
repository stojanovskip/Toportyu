package com.company;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;

import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Madi.Yessirkepov on 4/13/2016.
 */
public class ServerHandlerTest {

    private HttpExchange httpExchange = mock(HttpExchange.class);
    private ServerHandler serverHandler = new ServerHandler(httpExchange);

    @Test
    public void getRequestBodyTest() throws Exception {

        when(serverHandler.getRequestBody()).thenReturn("testBody");
        String testMessage = serverHandler.getRequestBody();
        assertEquals("testBody", testMessage);
    }

    @Test
    public void respond() throws Exception {
        HttpExchange httpExchange = mock(HttpExchange.class);
        OutputStream outputStream = mock(OutputStream.class);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        serverHandler.respond(200, "testMessage");

        verify(httpExchange).sendResponseHeaders(200, "testMessage".length());
        verify(outputStream).write("testMessage".getBytes());
        verify(outputStream).close();
    }
}
