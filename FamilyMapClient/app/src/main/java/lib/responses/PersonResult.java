package lib.responses;

import java.util.ArrayList;

import lib.models.Person;

public class PersonResult {

    /**
     * user name
     */
    public String associatedUsername;
    /**
     * person id
     */
    public String personID;
    /**
     * first name
     */
    public String firstName;
    /**
     * last name
     */
    public String lastName;
    /**
     * gender
     */
    public String gender;
    /**
     * father id
     */
    public String fatherID;
    /**
     * mother id
     */
    public String motherID;
    /**
     * spouse id
     */
    public String spouseID;

    public ArrayList<Person> data;

    /**
     * status message
     */
    public String message;

    /**
     * success or not
     */
    public boolean success = false;

}
