package requests;

public class RegisterRequest {

    /**
     * user name
     */
    public String userName;

    /**
     * password
     */
    public String password;

    /**
     * email
     */
    public String email;

    /**
     * first name
     */
    public String firstName;

    /**
     * last name
     */
    public String lastName;

    /**
     * gender
     */
    public String gender;

    public String getUserName() { return userName; }

    /**
     * Check if request is valid
     * @return success
     */
    public boolean checkIfValid()
    {
        if (userName == null)
            return false;
        else if (password == null)
            return false;
        else if (email == null)
            return false;
        else if (firstName == null)
            return false;
        else if (lastName == null)
            return false;
        else if (gender == null)
            return false;
        return true;
    }
}