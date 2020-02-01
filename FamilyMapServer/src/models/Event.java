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

    public Event(String eventID, String associatedUsername, String personID, String eventType){
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.eventType = eventType;
    }

    public Event(String eventID, String associatedUsername, String personID, String latitude,
                 String longitude, String country, String city, String eventType, String year){
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String toString(){
        return "'" + eventID + "','" +
                associatedUsername + "','" +
                personID + "','" +
                latitude + "','" +
                longitude + "','" +
                country + "','" +
                city + "','" +
                eventType + "','" +
                year + "'";
    }

}
