package requests;

import models.Event;
import models.Person;
import models.User;

import java.util.ArrayList;

public class LoadRequest {

    public ArrayList<User> users;
    public ArrayList<Person> persons;
    public ArrayList<Event> events;

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
