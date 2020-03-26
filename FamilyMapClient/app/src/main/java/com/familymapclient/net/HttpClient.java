package com.familymapclient.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import lib.requests.LoginRequest;
import lib.requests.RegisterRequest;
import lib.responses.LoginResult;
import lib.responses.RegisterResult;

public class HttpClient {

    private String ip;
    private String port;

    private static HttpClient instance;

    public static HttpClient getInstance(){
        return instance;
    }

    public static Boolean start(String ip, String port){
        if (instance == null)
        {
            instance = new HttpClient(ip, port);
            return true;
        }
        else
            return false;

    }

    private HttpClient(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public LoginResult login(LoginRequest request) {
        LoginResult result = new LoginResult();
        try {
            URL url = new URL("http://" + ip + ":" + port + "/user/login");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.connect();

            String request_string = new Gson().toJson(request);
            OutputStreamWriter sw = new OutputStreamWriter(connection.getOutputStream());
            sw.write(request_string);
            sw.flush();
            connection.getOutputStream().close();

            int responseCode = connection.getResponseCode();

            Scanner s;
            if (responseCode == HttpURLConnection.HTTP_OK)
                s = new Scanner(connection.getInputStream()).useDelimiter("\\A");
            else
                s = new Scanner(connection.getErrorStream()).useDelimiter("\\A");

            String response_string = s.hasNext() ? s.next() : "";
            result = new Gson().fromJson(response_string, LoginResult.class);
        } catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }

        return result;
    }

    public RegisterResult register(RegisterRequest request) {
        RegisterResult result = new RegisterResult();
        try {
            URL url = new URL("http://" + ip + ":" + port + "/user/register");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.connect();

            String request_string = new Gson().toJson(request);
            OutputStreamWriter sw = new OutputStreamWriter(connection.getOutputStream());
            sw.write(request_string);
            sw.flush();
            connection.getOutputStream().close();

            int responseCode = connection.getResponseCode();

            Scanner s;
            if (responseCode == HttpURLConnection.HTTP_OK)
                s = new Scanner(connection.getInputStream()).useDelimiter("\\A");
            else
                s = new Scanner(connection.getErrorStream()).useDelimiter("\\A");

            String response_string = s.hasNext() ? s.next() : "";
            result = new Gson().fromJson(response_string, RegisterResult.class);
        } catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }
        return result;
    }
}

