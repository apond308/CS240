package models;

public class Event {

    public String id;
    public String username;
    public String person_id;
    public String latitude;
    public String longitude;
    public String country;
    public String city;
    public String type;
    public String year;

    public Event(String id, String username, String person_id, String type){
        this.id = id;
        this.username = username;
        this.person_id = person_id;
        this.type = type;
    }

    public Event(String id, String username, String person_id, String latitude,
                 String longitude, String country, String city, String type, String year){
        this.id = id;
        this.username = username;
        this.person_id = person_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.type = type;
        this.year = year;
    }

    public String toString(){
        return "'" + id + "','" +
                username + "','" +
                person_id + "','" +
                latitude + "','" +
                longitude + "','" +
                country + "','" +
                city + "','" +
                type + "','" +
                year + "'";
    }

}
