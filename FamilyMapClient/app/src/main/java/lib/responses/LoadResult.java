package lib.responses;

import java.util.ArrayList;

import lib.models.Event;
import lib.models.Person;
import lib.models.User;

public class LoadResult {

    /**
     * list of users
     */
    ArrayList<User> users;
    /**
     * list of persons
     */
    ArrayList<Person> persons;
    /**
     * list of events
     */
    ArrayList<Event> events;

    /**
     * status message
     */
    public String message;

    /**
     * success or not
     */
    public boolean success = false;

}
