package models;

public class Person {

    public String id;
    public String username;
    public String first_name;
    public String last_name;
    public String gender;
    public String father_id;
    public String mother_id;
    public String spouse_id;

    public Person(String username) {
        this.username = username;
    }

    public String toString(){
        return "'" + id + "','" +
                username + "','" +
                first_name + "','" +
                last_name + "','" +
                gender + "','" +
                father_id + "','" +
                mother_id + "','" +
                spouse_id + "'";
    }

}
