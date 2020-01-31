package responses;

import models.Person;

import java.util.ArrayList;

public class PersonResult {

    public String associatedUsername;
    public String personID;
    public String firstName;
    public String lastName;
    public String gender;
    public String fatherID;
    public String motherID;
    public String spouseID;

    public ArrayList<Person> data;

    public String message;
    public boolean success = false;

}
