package responses;

import models.Event;
import models.Person;
import models.User;

import java.util.ArrayList;

public class LoadResult {

    ArrayList<User> users;
    ArrayList<Person> persons;
    ArrayList<Event> events;

    public String message;
    public boolean success = true;

}
