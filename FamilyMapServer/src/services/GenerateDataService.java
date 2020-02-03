package services;

import dao.EventDao;
import dao.PersonDao;
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

        Person father = new Person(UUID.randomUUID().toString(), current_person.personID, getName(male_name_path), current_person.lastName, "m");
        current_person.fatherID = father.associatedUsername;
        Event father_birth = new Event(UUID.randomUUID().toString(), father.personID, father.associatedUsername, "birth");
        EventDao.addEvent(father_birth);

        Person mother = new Person(UUID.randomUUID().toString(), current_person.personID, getName(female_name_path), getName(last_name_path), "f");
        current_person.motherID = mother.associatedUsername;
        Event mother_birth = new Event(UUID.randomUUID().toString(), father.personID, father.associatedUsername, "birth");
        EventDao.addEvent(mother_birth);

        father.spouseID = mother.associatedUsername;

        Event father_marriage = new Event(UUID.randomUUID().toString(), father.personID, father.associatedUsername, "marriage");
        Event mother_marriage = new Event(UUID.randomUUID().toString(), mother.personID, mother.associatedUsername, "marriage");
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
        current_person.fatherID = to_add.get(0).associatedUsername;
        current_person.motherID = to_add.get(1).associatedUsername;

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
