package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.LoadRequest;
import requests.LoginRequest;
import responses.ClearResult;
import responses.LoadResult;
import services.ClearService;
import services.LoadService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class LoadHandler implements HttpHandler {

    /**
     * Handler for load url
     * @param exchange HTTP request
     * @throws IOException request error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoadResult result = new LoadResult();

        if (!exchange.getRequestMethod().equals("POST")){
            result.message = "Error: Bad method";
            result.success = false;
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
        }
        if (result.success)
        {
            try {
                ClearResult clear_result = ClearService.clear();
                if (!clear_result.success)
                    throw new IOException();

                InputStream request_body = exchange.getRequestBody();
                Scanner s = new Scanner(request_body).useDelimiter("\\A");
                String body_string = s.hasNext() ? s.next() : "";

                System.out.println("LOAD REQUEST");

                LoadRequest request = new Gson().fromJson(body_string, LoadRequest.class);
                if (!request.checkIfValid())
                    throw new IOException();

                result = LoadService.load(request);
                if (result.success)
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                else
                    throw new IOException();

            } catch (IOException e) {
                result.message = "Error: Internal server error";
                result.success = false;
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                e.printStackTrace();
            }
        }

        String response_body = new Gson().toJson(result, LoadResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }
}
