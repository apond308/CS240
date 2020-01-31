package models;

import java.util.UUID;

public class AuthToken {

    private String username;
    private String token;

    public AuthToken(String name){
        username = name;
        token = UUID.randomUUID().toString();
    }

    public String getToken(){
        return token;
    }

    public String getUser(){
        return username;
    }

}
