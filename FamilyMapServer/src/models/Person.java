package models;

public class Person {

    public String associatedUsername;
    public String personID;
    public String firstName;
    public String lastName;
    public String gender;
    public String fatherID;
    public String motherID;
    public String spouseID;

    public Person(String associatedUsername, String personID, String firstName, String lastName, String gender) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String toString(){
        return "'" + personID + "','" +
                associatedUsername + "','" +
                firstName + "','" +
                lastName + "','" +
                gender + "','" +
                fatherID + "','" +
                motherID + "','" +
                spouseID + "'";
    }

}
