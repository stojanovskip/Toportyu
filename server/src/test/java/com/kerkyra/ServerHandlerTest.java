
package com.kerkyra;

import com.kerkyra.httpserver.ServerHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


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

    @Test
    public void getRequestHeadersTest() throws Exception {
        Headers headerTest = new Headers();
        headerTest.add("1", "2");

        when(httpExchange.getRequestHeaders()).thenReturn(headerTest);

        assertEquals(serverHandler.getRequestHeaders(), headerTest);
    }

    @Test
    public void getResponseHeadersTest() throws Exception {
        Headers headerTest = new Headers();
        headerTest.add("1", "2");

        when(httpExchange.getResponseHeaders()).thenReturn(headerTest);

        assertEquals(serverHandler.getResponseHeaders(), headerTest);
    }

    @Test
    public void isPostTest() {
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        assertEquals(true, serverHandler.isPost());
    }

    @Test
    public void isGetTest() {
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        assertEquals(true, serverHandler.isGet());
    }

}

