package requests;

public class FillRequest {

    /**
     * user name
     */
    public String username;

    /**
     * generations
     */
    public int generations;

    /**
     * @param username username provided
     * @param generations how many generations to fill
     */
    public FillRequest(String username, int generations){
        this.username = username;
        this.generations = generations;
    }

}
