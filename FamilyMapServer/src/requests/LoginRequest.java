package requests;

public class LoginRequest {

    public String userName;
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