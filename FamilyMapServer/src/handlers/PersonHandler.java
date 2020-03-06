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

    /**
     * Handler for person url
     * @param exchange HTTP request
     * @throws IOException request error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PersonResult result = new PersonResult();
        try {

            if (!exchange.getRequestMethod().equals("GET")) {
                result.message = "Error: Bad method";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                throw new IOException();
            }

            Headers headers = exchange.getRequestHeaders();
            if (!headers.containsKey("Authorization")) {
                result.message = "Error: Invalid auth token";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                throw new IOException();
            }

            String token = headers.getFirst("Authorization");
            String username = AuthTokenDao.getUserFromToken(token);
            if (username == null) {
                result.message = "Error: Invalid auth token";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                throw new IOException();
            }

            URI uri = exchange.getRequestURI();
            String[] segments = uri.getPath().split("/");


            if (segments.length == 3) {
                String id = segments[2];
                Person p = PersonDao.getPerson(id);
                assert p != null;
                if (!p.associatedUsername.equals(username)) {
                    result.message = "Error: Invalid auth token";
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    throw new IOException();
                }
                result.associatedUsername = p.associatedUsername;
                result.personID = p.personID;
                result.firstName = p.firstName;
                result.lastName = p.lastName;
                result.gender = p.gender;
                result.fatherID = p.fatherID;
                result.motherID = p.motherID;
                result.spouseID = p.spouseID;
            } else
                result.data = PersonDao.getFamily(username);
            result.success = true;
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } catch (IOException e) {
            result.success = false;
        }

        String response_body = new Gson().toJson(result, PersonResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();

    }
}
