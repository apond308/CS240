package models;

public class Event {

    /**
     * event id
     */
    public String eventID;

    /**
     * username associated with event
     */
    public String associatedUsername;

    /**
     * person id
     */
    public String personID;

    /**
     * latitude of event
     */
    public String latitude;

    /**
     * longitude of event
     */
    public String longitude;

    /**
     * country of event
     */
    public String country;

    /**
     * city of event
     */
    public String city;

    /**
     * type of event
     */
    public String eventType;

    /**
     * year of event
     */
    public String year;

    /**
     * Create new event
     * @param eventID id of event
     * @param associatedUsername user name
     * @param personID person id
     * @param eventType type of event
     * @param year year of event
     */
    public Event(String eventID, String associatedUsername, String personID, String latitude, String longitude, String eventType, String year){
        this.eventID = eventID.equals("") ? null : eventID;
        this.associatedUsername = associatedUsername.equals("") ? null : associatedUsername;
        this.personID = personID.equals("") ? null : personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.eventType = eventType.equals("") ? null : eventType;
        this.year = year.equals("") ? null : year;
    }

    /**
     *
     * @param eventID id of event
     * @param associatedUsername user name
     * @param personID person id
     * @param latitude latitude
     * @param longitude longitude
     * @param country country
     * @param city city
     * @param eventType type of event
     * @param year year of event
     * */
    public Event(String eventID, String associatedUsername, String personID, String latitude,
                 String longitude, String country, String city, String eventType, String year){
        this.eventID = eventID.equals("") ? null : eventID;
        this.associatedUsername = associatedUsername.equals("") ? null : associatedUsername;
        this.personID = personID.equals("") ? null : personID;
        this.latitude = latitude.equals("") ? null : latitude;
        this.longitude = longitude.equals("") ? null : longitude;
        this.country = country.equals("") ? null : country;
        this.city = city.equals("") ? null : city;
        this.eventType = eventType.equals("") ? null : eventType;
        this.year = year.equals("") ? null : year;
    }

    /**
     * convert event to string
     * @return string of event
     */
    public String toString(){
        return "'" + eventID + "','" +
                associatedUsername + "','" +
                personID + "','" +
                (latitude==null ? "" : latitude) + "','" +
                (longitude==null ? "" : longitude) + "','" +
                (country==null ? "" : country) + "','" +
                (city==null ? "" : city) + "','" +
                (eventType==null ? "" : eventType) + "','" +
                (year==null ? "" : year) + "'";
    }

}
