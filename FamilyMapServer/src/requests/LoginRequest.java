package requests;

public class LoginRequest {

    /**
     * user name
     */
    public String userName;

    /**
     * password
     */
    public String password;

    /**
     * Check if request is valid
     * @return success
     */
    public boolean checkIfValid(){
        if (userName == null)
            return false;
        else if (password == null)
            return false;

        return true;
    }
}