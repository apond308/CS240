package models;

import java.util.UUID;

/**
 * Authorization token class
 */
public class AuthToken {

    /**
     * user name
     */
    private String username;

    /**
     * Create new auth token
     * @param name user name
     */
    public AuthToken(String name){
        username = name;
    }

    /**
     * get user name of token
     * @return username
     */
    public String getUser(){
        return username;
    }

}
