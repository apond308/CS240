package requests;

public class FillRequest {

    public String username;
    public int generations;

    public FillRequest(String username, int generations){
        this.username = username;
        this.generations = generations;
    }

}
