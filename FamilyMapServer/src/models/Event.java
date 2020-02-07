package models;

public class Event {

    public String eventID;
    public String associatedUsername;
    public String personID;
    public String latitude;
    public String longitude;
    public String country;
    public String city;
    public String eventType;
    public String year;

    public Event(String eventID, String associatedUsername, String personID, String eventType, String year){
        this.eventID = eventID.equals("") ? null : eventID;
        this.associatedUsername = associatedUsername.equals("") ? null : associatedUsername;
        this.personID = personID.equals("") ? null : personID;
        this.eventType = eventType.equals("") ? null : eventType;
        this.year = year.equals("") ? null : year;
    }

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
