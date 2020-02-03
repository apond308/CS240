package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.AuthTokenDao;
import dao.PersonDao;
import models.Person;
import responses.PersonResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class PersonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PersonResult result = new PersonResult();

        if (!exchange.getRequestMethod().equals("GET")){
            result.message = "Bad method";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (!headers.containsKey("Authorization")){
            result.message = "Invalid auth token";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        String token = headers.getFirst("Authorization");
        String username = AuthTokenDao.getUserFromToken(token);

        System.out.println("PERSON REQUEST");

        URI uri = exchange.getRequestURI();
        String[] segments = uri.getPath().split("/");


        if (segments.length == 3) {
            String id = segments[2];
            Person p = PersonDao.getPerson(id);
            result.associatedUsername = p.personID;
            result.personID = p.associatedUsername;
            result.firstName = p.firstName;
            result.lastName = p.lastName;
            result.gender = p.gender;
            result.fatherID = p.fatherID;
            result.motherID = p.motherID;
            result.spouseID = p.spouseID;
        }
        else
            result.data = PersonDao.getAllPersons(username);

        result.success = true;
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, PersonResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();

    }
}
