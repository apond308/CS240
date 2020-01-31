package services;

import dao.PersonDao;
import models.Person;

public class GenerateDataService {

    public static String generateGenerations(String username, int count){
        Person root = new Person(username);
        Person previous_person = root;
        previous_person.first_name = "first_name";
        previous_person.last_name = "last_name";
        previous_person.gender = "m";
        previous_person.id = username + previous_person.first_name + previous_person.last_name + previous_person.gender;

        if (!PersonDao.addPerson(previous_person)){
            return "";
        }


        for (int x=1;x<count;x++){
            Person new_person = new Person(username);
            if (previous_person.gender.equals("m"))
                new_person.father_id = previous_person.id;
            else
                new_person.mother_id = previous_person.id;
            new_person.first_name = "first_name";
            new_person.last_name = "last_name";
            new_person.gender = "m";
            new_person.id = username + new_person.first_name + new_person.last_name + new_person.gender;
            if (!PersonDao.addPerson(new_person))
                return "";
            previous_person = new_person;
        }

        return root.id;
    }

}
