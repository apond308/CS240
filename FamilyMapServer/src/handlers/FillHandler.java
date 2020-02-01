package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.FillRequest;
import responses.FillResult;
import responses.LoginResult;
import services.FillService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        FillResult result = new FillResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Bad request";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        System.out.println("FILL REQUEST");

        URI uri = exchange.getRequestURI();
        String[] segments = uri.getPath().split("/");
        String username = segments[2];
        int generations;
        if(segments.length == 3) {
            generations = 4;
        } else {
            generations = Integer.parseInt(segments[3]);
        }

        result = FillService.fill(new FillRequest(username, generations));

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, FillResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();

    }
}
