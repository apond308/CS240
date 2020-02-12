package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.AuthTokenDao;
import dao.EventDao;
import models.Event;
import responses.EventResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class EventHandler implements HttpHandler {

    /**
     * Handler for events url
     * @param exchange HTTP request
     * @throws IOException request error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        EventResult result = new EventResult();

        try {

            if (!exchange.getRequestMethod().equals("GET")) {
                result.message = "Error: Bad method";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                throw new IOException();
            }

            Headers headers = exchange.getRequestHeaders();
            if (!headers.containsKey("Authorization")) {
                result.message = "Error: Invalid auth token";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                throw new IOException();
            }

            String token = headers.getFirst("Authorization");
            String username = AuthTokenDao.getUserFromToken(token);
            if (username == null) {
                result.message = "Error: Invalid auth token";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                throw new IOException();
            }


            System.out.println("EVENT REQUEST");

            URI uri = exchange.getRequestURI();
            String[] segments = uri.getPath().split("/");

            if (segments.length == 3) {
                String id = segments[2];
                Event e = EventDao.getEvent(id);
                assert e != null;
                if (!e.associatedUsername.equals(username)) {
                    result.message = "Error: Invalid auth token";
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    throw new IOException();
                }
                result.associatedUsername = e.associatedUsername;
                result.eventID = e.eventID;
                result.personID = e.personID;
                result.latitude = e.latitude;
                result.longitude = e.longitude;
                result.country = e.country;
                result.city = e.city;
                result.eventType = e.eventType;
                result.year = e.year;
            } else {
                result.data = EventDao.getAllEvents(username);
            }
            result.success = true;
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } catch (IOException e) {
            result.success = false;
        }

        String response_body = new Gson().toJson(result, EventResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }

}
