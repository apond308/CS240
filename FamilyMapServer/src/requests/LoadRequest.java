package requests;

import models.Event;
import models.Person;
import models.User;

import java.util.ArrayList;

public class LoadRequest {

    /**
     * user list
     */
    public ArrayList<User> users = new ArrayList<>();

    /**
     * person list
     */
    public ArrayList<Person> persons = new ArrayList<>();

    /**
     * event list
     */
    public ArrayList<Event> events = new ArrayList<>();

    /**
     * Check if request is valid
     * @return success
     */
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
