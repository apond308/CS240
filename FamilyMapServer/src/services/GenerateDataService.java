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

    private static ArrayList<Person> generateParents(Person current_person, String birth_year, int depth){
        ArrayList<Person> parents = new ArrayList<>();


        Person father = new Person(UUID.randomUUID().toString(), current_person.associatedUsername, getName(male_name_path), current_person.lastName, "m");
        current_person.fatherID = father.personID;


        Event father_birth = new Event(UUID.randomUUID().toString(), father.associatedUsername, father.personID, "birth", Person.generateBirth(birth_year));
        EventDao.addEvent(father_birth);
        Event father_death = new Event(UUID.randomUUID().toString(), father.associatedUsername, father.personID, "death", Person.generateDeath(father_birth.year));
        EventDao.addEvent(father_death);


        Person mother = new Person(UUID.randomUUID().toString(), current_person.associatedUsername, getName(female_name_path), getName(last_name_path), "f");
        current_person.motherID = mother.personID;

        Event mother_birth = new Event(UUID.randomUUID().toString(), mother.associatedUsername, mother.personID, "birth", Person.generateBirth(birth_year));
        EventDao.addEvent(mother_birth);
        Event mother_death = new Event(UUID.randomUUID().toString(), mother.associatedUsername, mother.personID, "death", Person.generateDeath(mother_birth.year));
        EventDao.addEvent(mother_death);


        father.spouseID = mother.personID;
        mother.spouseID = father.personID;

        Event father_marriage = new Event(UUID.randomUUID().toString(), father.associatedUsername, father.personID, "marriage", Person.generateMarriage(father_birth.year));
        Event mother_marriage = new Event(UUID.randomUUID().toString(), mother.associatedUsername, mother.personID, "marriage", Person.generateMarriage(mother_birth.year));
        EventDao.addEvent(father_marriage);
        EventDao.addEvent(mother_marriage);

        parents.add(father);
        parents.add(mother);

        if (depth > 1) {
            parents.addAll(generateParents(father, father_birth.year, depth - 1));
            parents.addAll(generateParents(mother, mother_birth.year, depth - 1));
        }

        return parents;
    }

    public static int generateGenerations(String username, int generation_count){
        int added_count = 0;
        Person current_person = PersonDao.getUserPerson(username);

        assert current_person != null;
        Event person_birth = new Event(UUID.randomUUID().toString(), current_person.associatedUsername, current_person.personID, "birth", Person.generateBirth("2012"));
        EventDao.addEvent(person_birth);
        Event person_death = new Event(UUID.randomUUID().toString(), current_person.associatedUsername, current_person.personID, "death", Person.generateDeath(person_birth.year));
        EventDao.addEvent(person_death);

        ArrayList<Person> to_add = generateParents(current_person, person_birth.year, generation_count);
        current_person.fatherID = to_add.get(0).personID;
        current_person.motherID = to_add.get(1).personID;

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
