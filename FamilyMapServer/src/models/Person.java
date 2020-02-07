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

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID.equals("") ? null : personID;
        this.associatedUsername = associatedUsername.equals("") ? null : associatedUsername;
        this.firstName = firstName.equals("") ? null : firstName;
        this.lastName = lastName.equals("") ? null : lastName;
        this.gender = gender.equals("") ? null : gender;
        this.fatherID = fatherID.equals("") ? null : fatherID;
        this.motherID = motherID.equals("") ? null : motherID;
        this.spouseID = spouseID.equals("") ? null : spouseID;
    }

    public String toString(){
        return "'" + personID + "','" +
                associatedUsername + "','" +
                firstName + "','" +
                lastName + "','" +
                (gender==null ? "" : gender) + "','" +
                (fatherID==null ? "" : fatherID) + "','" +
                (motherID==null ? "" : motherID) + "','" +
                (spouseID==null ? "" : spouseID) + "'";
    }

    public static String generateBirth(String child_birth){
        int parent_year = Integer.parseInt(child_birth);
        return String.valueOf(parent_year-20);
    }

    public static String generateDeath(String birth){
        int birth_year = Integer.parseInt(birth);
        return String.valueOf(birth_year+90);
    }

    public static String generateMarriage(String birth){
        int birth_year = Integer.parseInt(birth);
        return String.valueOf(birth_year+30);
    }
}
