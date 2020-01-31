package services;

import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import models.Event;
import models.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class GenerateDataService {

    final static String male_name_path = new File("").getAbsolutePath() + "/data/names/male.txt";
    final static String female_name_path = new File("").getAbsolutePath() + "/data/names/female.txt";
    final static String last_name_path = new File("").getAbsolutePath() + "/data/names/last.txt";

    private static ArrayList<Person> generateParents(Person current_person, int depth){
        ArrayList<Person> parents = new ArrayList<>();

        Person father = new Person(UUID.randomUUID().toString(), current_person.username, getName(male_name_path), current_person.last_name, "m");
        current_person.father_id = father.id;
        Event father_birth = new Event(UUID.randomUUID().toString(), father.username, father.id, "birth");
        EventDao.addEvent(father_birth);

        Person mother = new Person(UUID.randomUUID().toString(), current_person.username, getName(female_name_path), getName(last_name_path), "f");
        current_person.mother_id = mother.id;
        Event mother_birth = new Event(UUID.randomUUID().toString(), father.username, father.id, "birth");
        EventDao.addEvent(mother_birth);

        father.spouse_id = mother.id;

        Event father_marriage = new Event(UUID.randomUUID().toString(), father.username, father.id, "marriage");
        Event mother_marriage = new Event(UUID.randomUUID().toString(), mother.username, mother.id, "marriage");
        EventDao.addEvent(father_marriage);
        EventDao.addEvent(mother_marriage);

        parents.add(father);
        parents.add(mother);

        if (depth > 0) {
            parents.addAll(generateParents(father, depth - 1));
            parents.addAll(generateParents(mother, depth - 1));
        }

        return parents;
    }

    public static int generateGenerations(String username, int generation_count){
        int added_count = 0;
        Person current_person = PersonDao.getUserPerson(username);

        assert current_person != null;
        ArrayList<Person> to_add = generateParents(current_person, generation_count);
        current_person.father_id = to_add.get(0).id;
        current_person.mother_id = to_add.get(1).id;

        PersonDao.deleteUserPerson(username);
        to_add.add(0, current_person);
        for (Person p : to_add) {
            PersonDao.addPerson(p);
            added_count++;
        }
        return added_count;
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
