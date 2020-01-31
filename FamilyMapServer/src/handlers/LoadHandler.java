package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.LoadRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equals("POST")){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        boolean success = true;
        try {

            InputStream request_body = exchange.getRequestBody();

            Scanner s = new Scanner(request_body).useDelimiter("\\A");
            String body_string = s.hasNext() ? s.next() : "";

            Gson gson = new Gson();
            requests.LoadRequest request = gson.fromJson(body_string, LoadRequest.class);
            success = request.checkIfValid();
        }
        catch (JsonSyntaxException e) {
            System.out.println("Bad json formatting in request body");
            success = false;
        }

        if (!success) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            return;
        }

        // LOAD

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        exchange.getResponseBody().close();
    }
}
