package models;

public class User {

    public String personID;
    public String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

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
