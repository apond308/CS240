package services;

import dao.PersonDao;
import models.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GenerateDataService {

    final static String male_name_path = new File("").getAbsolutePath() + "/data/names/male.txt";
    final static String female_name_path = new File("").getAbsolutePath() + "/data/names/female.txt";
    final static String last_name_path = new File("").getAbsolutePath() + "/data/names/last.txt";

    public static String generateGenerations(String username, int count){
        Person root = new Person(username);
        Person previous_person = root;
        previous_person.first_name = getName(male_name_path);
        previous_person.last_name = getName(last_name_path);
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

    public static String getName(String path) {
        ArrayList<String> name_list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String[] input_file = Files.readString(Paths.get(path)).split("\\s+");
            name_list.addAll(Arrays.asList(input_file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = new Random().nextInt(name_list.size()-1);
        return name_list.get(index);
    }

}
