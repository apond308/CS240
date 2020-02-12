package models;

import java.util.UUID;

/**
 * Authorization token class
 */
public class AuthToken {

    private String username;
    private String token;

    /**
     * Create new auth token
     * @param name user name
     */
    public AuthToken(String name){
        username = name;
        token = UUID.randomUUID().toString();
    }

    /**
     * get user name of token
     * @return username
     */
    public String getUser(){
        return username;
    }

}
