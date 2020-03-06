package responses;

import models.Event;
import java.util.ArrayList;

public class EventResult {

    /**
     * user name of event
     */
    public String associatedUsername;
    /**
     * id of event
     */
    public String eventID;
    /**
     * id of person of event
     */
    public String personID;
    /**
     * latitude
     */
    public String latitude;
    /**
     * longitude
     */
    public String longitude;
    /**
     * country
     */
    public String country;
    /**
     * city
     */
    public String city;
    /**
     * type
     */
    public String eventType;
    /**
     * year
     */
    public String year;

    /**
     * list of event data
     */
    public ArrayList<Event> data;

    /**
     * status message
     */
    public String message;

    /**
     * success or not
     */
    public boolean success = false;

}
