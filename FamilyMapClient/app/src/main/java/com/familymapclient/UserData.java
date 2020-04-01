package com.familymapclient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import lib.models.Event;
import lib.models.Person;
import lib.models.User;

public class UserData {

    private static UserData instance = null;

    public static UserData getInstance(){
        if (instance == null)
            instance = new UserData();
        return instance;
    }
    public String auth_token = "";

    public Person person;

    public ArrayList<Person> person_list;
    public ArrayList<Event> event_list;

    public boolean life_story_lines = true;
    public boolean family_tree_lines = true;
    public boolean spouse_lines = true;
    public boolean fathers_side = true;
    public boolean mothers_side = true;
    public boolean male_events = true;
    public boolean female_events = true;

    private HashSet<String> getParentIDs(String id) {
        HashSet<String> id_set = new HashSet<>();
        if (id == null || id.equals(""))
            return id_set;
        for (Person p : person_list){
            if (p.personID.equals(id))
            {
                if (p.gender.equals("m") && UserData.getInstance().male_events)
                    id_set.add(id);
                if (p.gender.equals("f") && UserData.getInstance().female_events)
                    id_set.add(id);

                id_set.addAll(getParentIDs(p.fatherID));
                id_set.addAll(getParentIDs(p.motherID));
            }
        }
        return id_set;
    }

    public ArrayList<Event> getFilteredEvents(){
        ArrayList<Event> filtered_events = new ArrayList<>();

        UserData user_data = UserData.getInstance();

        HashSet<String> valid_ids = new HashSet<>();
        valid_ids.add(user_data.person.personID);

        if (user_data.fathers_side)
            valid_ids.addAll(getParentIDs(person.fatherID));
        else if (user_data.mothers_side)
            valid_ids.addAll(getParentIDs(person.motherID));

        for (Event e : event_list){
            if (valid_ids.contains(e.personID))
                filtered_events.add(e);
        }



        return filtered_events;
    }
}
