package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import responses.ClearResult;
import services.ClearService;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    /**
     * Handler for clear url
     * @param exchange HTTP request
     * @throws IOException request error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ClearResult result = new ClearResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Error: Bad method";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            return;
        }

        System.out.println("CLEAR REQUEST");

        result = ClearService.clear();

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        String response_body = new Gson().toJson(result, ClearResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }
}
