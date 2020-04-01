package lib.models;

public class User {

    /**
     * person id
     */
    public String personID;

    /**
     * user name
     */
    public String userName;

    /**
     * password
     */
    private String password;

    /**
     * email
     */
    private String email;

    /**
     * first name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;

    /**
     * gender
     */
    private String gender;

    /**
     * Create new User
     * @param personID id
     * @param userName user name
     * @param password password
     * @param email email
     * @param firstName first name
     * @param lastName last name
     * @param gender gender
     */
    public User(String personID,
                String userName,
                String password,
                String email,
                String firstName,
                String lastName,
                String gender)
    {
        this.personID = personID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * convert user to string
     * @return user string
     */
    public String toString(){
        return "'" + personID + "','" +
                userName + "','" +
                password + "','" +
                email + "','" +
                firstName + "','" +
                lastName + "','" +
                gender + "'";
    }

    public String getPassword() {return password;}

}
