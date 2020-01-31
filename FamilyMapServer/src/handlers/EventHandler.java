package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import responses.EventResult;
import responses.FillResult;

import java.io.IOException;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        EventResult result = new EventResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Bad method";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
        }
        else{


            result.message = "list: here";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }

        String response_body = new Gson().toJson(result, EventResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }

}
