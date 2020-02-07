package services;

import dao.AuthTokenDao;
import dao.PersonDao;
import dao.UserDao;
import models.Person;
import models.User;
import requests.RegisterRequest;
import responses.RegisterResult;

import java.util.UUID;

public class RegisterService {

    public static RegisterResult register(RegisterRequest r) {

        User new_user = new User(UUID.randomUUID().toString(), r.userName, r.password, r.email,
                r.firstName, r.lastName, r.gender);

        RegisterResult result = new RegisterResult();

        User test_user = UserDao.getUser(r.userName);
        if (test_user != null && !test_user.userName.equals(""))
        {
            result.message = "Error: Username already taken by another user";
            result.success = false;
            return result;
        }

        result.authToken = UUID.randomUUID().toString();
        AuthTokenDao.updateToken(r.userName, result.authToken);

        Person p = new Person(new_user.personID, r.userName, r.firstName, r.lastName, r.gender);
        PersonDao.addPerson(p);

        result.userName = r.userName;
        result.personID = new_user.personID;
        UserDao.addUser(new_user);

        // Generate 4 generations of ancestry
        GenerateDataService.generateGenerations(r.userName, 4);

        result.success = true;

//        else {
//            result.message = "Internal server error";
//            result.success = false;
//        }

        return result;
    }

}
