
package com.kerkyra;

import com.google.gson.Gson;
import com.kerkyra.httpserver.ServerHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private OutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = mock(OutputStream.class);
        httpExchange = mock(HttpExchange.class);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

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
    public void respondJsonTest() throws Exception {
        Headers headers = mock(Headers.class);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);

        serverHandler.respondJson(200, "testMessage");

        verify(headers).add("Content-Type", "application/json");
        verifyRespondSent(200, new Gson().toJson("testMessage"));
    }

    @Test
    public void respondTest() throws Exception {
        serverHandler.respond(200, "testMessage");
        verifyRespondSent(200, "testMessage");
    }

    private void verifyRespondSent(int statusCode, String message) throws IOException {
        verify(httpExchange).sendResponseHeaders(statusCode, message.length());
        verify(outputStream).write(message.getBytes());
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
        ServerHandler serverHandler = new ServerHandler(httpExchange);
        assertEquals(true, serverHandler.isPost());
    }

    @Test
    public void isGetTest() {
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        ServerHandler serverHandler = new ServerHandler(httpExchange);
        assertEquals(true, serverHandler.isGet());
    }

}

