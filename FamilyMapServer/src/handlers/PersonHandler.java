package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.PersonDao;
import models.Person;
import responses.FillResult;
import responses.PersonResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class PersonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PersonResult result = new PersonResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Bad method";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        System.out.println("PERSON REQUEST");

        URI uri = exchange.getRequestURI();
        String[] segments = uri.getPath().split("/");


        if (segments.length == 3) {
            String id = segments[2];
            Person p = PersonDao.getPerson(id);
            result.associatedUsername = p.username;
            result.personID = p.id;
            result.firstName = p.first_name;
            result.lastName = p.last_name;
            result.gender = p.gender;
            result.fatherID = p.father_id;
            result.motherID = p.mother_id;
            result.spouseID = p.spouse_id;
        }
        else
            result.data = PersonDao.getAllPersons();

        result.success = true;
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, PersonResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();

    }
}
