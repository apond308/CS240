package responses;

import models.Event;
import models.Person;
import models.User;

import java.util.ArrayList;

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
