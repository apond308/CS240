package services;

import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import models.Event;
import models.Person;
import models.User;
import requests.LoadRequest;
import responses.LoadResult;

import java.io.IOException;

public class LoadService {
    /**
     * Load data from provided JSON
     * @param request request data
     * @return result of load request
     */
    public static LoadResult load(LoadRequest request) {
        LoadResult result = new LoadResult();

        try {
            for (User user : request.users) {
                if (!UserDao.addUser(user))
                    throw new IOException();
            }
            for (Person person : request.persons) {
                PersonDao.addPerson(person);
            }
            for (Event event : request.events) {
                EventDao.addEvent(event);
            }
        } catch (IOException e) {
            result.success = false;
            return result;
        }

        result.message = "Successfully added " + request.users.size() +
                " users, " + request.persons.size() + " persons, and " +
                request.events.size() + " events to the database.";
        result.success = true;
        return result;
    }

}
