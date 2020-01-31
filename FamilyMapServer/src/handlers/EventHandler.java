package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.EventDao;
import dao.PersonDao;
import models.Event;
import models.Person;
import responses.EventResult;
import responses.FillResult;

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

        URI uri = exchange.getRequestURI();
        String[] segments = uri.getPath().split("/");

        if (segments.length == 3) {
            System.out.println("done setting persons");
            String id = segments[2];
            Event e = EventDao.getEvent(id);
            assert e != null;
            result.associatedUsername = e.username;
            result.eventID = e.id;
            result.personID = e.person_id;
            result.latitude = e.latitude;
            result.longitude = e.longitude;
            result.country = e.country;
            result.city = e.city;
            result.type = e.type;
            result.year = e.year;
        }
        else
            result.data = EventDao.getAllEvents();

        result.success = true;
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, EventResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }

}
