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

    public Person(String id, String username, String first_name, String last_name, String gender) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    public Person(String id, String username, String first_name, String last_name, String gender, String father_id, String mother_id, String spouse_id) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.father_id = father_id;
        this.mother_id = mother_id;
        this.spouse_id = spouse_id;
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
