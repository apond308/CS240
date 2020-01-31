package services;

import dao.AuthTokenDao;
import dao.UserDao;
import models.User;
import requests.RegisterRequest;
import responses.RegisterResult;

import java.util.UUID;

public class RegisterService {

    public static RegisterResult register(RegisterRequest r) {

        User new_user = new User("", r.userName, r.password, r.email,
                r.firstName, r.lastName, r.gender);

        RegisterResult result = new RegisterResult();

        if (UserDao.getUser(r.userName) != null)
        {
            System.out.println("User already in database.");
            result.message = "Username already taken by another user";
            result.success = false;
            return result;
        }

        result.authToken = UUID.randomUUID().toString();
        AuthTokenDao.updateToken(r.userName, result.authToken);
        result.userName = r.userName;
        String id = GenerateDataService.generateGenerations(result.userName, 4);
        if (id.equals(""))
            return result;
        new_user.personID = id;
        result.personID = id;
        UserDao.addUser(new_user);
        result.success = true;

//        else {
//            result.message = "Internal server error";
//            result.success = false;
//        }

        return result;
    }

}
