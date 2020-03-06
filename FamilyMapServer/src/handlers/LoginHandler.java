package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import requests.LoginRequest;
import responses.LoginResult;
import services.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class LoginHandler implements HttpHandler {

    /**
     * Handler for login url
     * @param exchange HTTP request
     * @throws IOException request error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoginResult result = new LoginResult();
        try {

            if (!exchange.getRequestMethod().equals("POST")){
                result.message = "Error: Bad method";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                throw new Exception();
            }

            InputStream request_body = exchange.getRequestBody();
            Scanner s = new Scanner(request_body).useDelimiter("\\A");
            String body_string = s.hasNext() ? s.next() : "";

            LoginRequest request = new Gson().fromJson(body_string, LoginRequest.class);

            if (!request.checkIfValid()) {
                result.message = "Error: Request property missing or has invalid value";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                throw new Exception();
            }

            result = LoginService.login(request);
            if (result.success)
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            else
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        catch (JsonSyntaxException e) {
            System.out.println("Error: Bad json formatting in request body");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response_body = new Gson().toJson(result, LoginResult.class);
        exchange.getResponseBody().write(response_body.getBytes());
        exchange.getResponseBody().close();
    }
}
