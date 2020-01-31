package responses;

import models.Event;
import java.util.ArrayList;

public class EventResult {

    public String associatedUsername;
    public String eventID;
    public String personID;
    public String latitude;
    public String longitude;
    public String country;
    public String city;
    public String type;
    public String year;

    public ArrayList<Event> data;

    public String message;
    public boolean success = false;

}
