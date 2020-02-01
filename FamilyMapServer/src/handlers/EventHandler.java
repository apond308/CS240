package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.EventDao;
import models.Event;
import responses.EventResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class EventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        EventResult result = new EventResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Bad method";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        System.out.println("EVENT REQUEST");

        URI uri = exchange.getRequestURI();
        String[] segments = uri.getPath().split("/");

        if (segments.length == 3) {
            System.out.println("Getting one person");
            String id = segments[2];
            Event e = EventDao.getEvent(id);
            assert e != null;
            result.associatedUsername = e.associatedUsername;
            result.eventID = e.eventID;
            result.personID = e.personID;
            result.latitude = e.latitude;
            result.longitude = e.longitude;
            result.country = e.country;
            result.city = e.city;
            result.type = e.eventType;
            result.year = e.year;
        }
        else {
            System.out.println("Getting all persons");
            result.data = EventDao.getAllEvents();
        }

        result.success = true;
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, EventResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }

}
