package models;

public class Person {

    /**
     * user name
     */
    public String associatedUsername;

    /**
     * person id
     */
    public String personID;

    /**
     * first name of person
     */
    public String firstName;

    /**
     * last name of person
     */
    public String lastName;

    /**
     * gender
     */
    public String gender;

    /**
     * father id
     */
    public String fatherID;

    /**
     * mother id
     */
    public String motherID;

    /**
     * spouse id
     */
    public String spouseID;

    /**
     * Create new person
     * @param personID id
     * @param associatedUsername user name
     * @param firstName first name
     * @param lastName last name
     * @param gender gender
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Create new person
     * @param personID id
     * @param associatedUsername user name
     * @param firstName first name
     * @param lastName last name
     * @param gender gender
     * @param fatherID father id
     * @param motherID mother id
     * @param spouseID spouse id
     */
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

    /**
     * convert person to string
     * @return string of person
     */
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

    /**
     * generate a birth date
     * @param child_birth date of child's birth
     * @return date of birth
     */
    public static String generateBirth(String child_birth){
        int parent_year = Integer.parseInt(child_birth);
        return String.valueOf(parent_year-20);
    }

    /**
     * generate a birth date
     * @param birth date of birth
     * @return date of death
     */
    public static String generateDeath(String birth){
        int birth_year = Integer.parseInt(birth);
        return String.valueOf(birth_year+90);
    }

    /**
     * generate marriage date
     * @param birth birth date
     * @return marriage date
     */
    public static String generateMarriage(String birth){
        int birth_year = Integer.parseInt(birth);
        return String.valueOf(birth_year+30);
    }
}
