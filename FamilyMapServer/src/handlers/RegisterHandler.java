package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import requests.RegisterRequest;
import responses.RegisterResult;
import services.RegisterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        RegisterResult result = new RegisterResult();
        try {

            if (!exchange.getRequestMethod().equals("POST")){
                result.message = "Bad method";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                throw new Exception();
            }

            InputStream request_body = exchange.getRequestBody();
            Scanner s = new Scanner(request_body).useDelimiter("\\A");
            String body_string = s.hasNext() ? s.next() : "";

            RegisterRequest request = new Gson().fromJson(body_string, RegisterRequest.class);

            if (!request.checkIfValid()) {
                result.message = "Request property missing or has invalid value";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                throw new Exception();
            }

            result = RegisterService.register(request);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
        }
        catch (JsonSyntaxException e) {
            System.out.println("Bad json formatting in request body");
        } catch (Exception e) {
            e.printStackTrace();
        }


        String response_body = new Gson().toJson(result, RegisterResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }
}
