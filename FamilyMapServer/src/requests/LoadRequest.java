package requests;

import models.Event;
import models.Person;
import models.User;

import java.util.ArrayList;

public class LoadRequest {

    ArrayList<User> users;
    ArrayList<Person> persons;
    ArrayList<Event> events;

    public boolean checkIfValid(){
        if (users == null)
            return false;
        if (persons == null)
            return false;
        if (events == null)
            return false;

        return true;
    }

}
