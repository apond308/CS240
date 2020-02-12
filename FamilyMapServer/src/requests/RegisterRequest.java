package requests;

public class RegisterRequest {

    public String userName;
    public String password;
    public String email;
    public String firstName;
    public String lastName;
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