package services;

import dao.AuthTokenDao;
import dao.UserDao;
import models.User;
import requests.LoginRequest;
import responses.LoginResult;

import java.util.Objects;
import java.util.UUID;

public class LoginService {

    public static LoginResult login(LoginRequest r){
        LoginResult result = new LoginResult();

        User test_user = UserDao.getUser(r.userName);
        if (test_user == null)
        {
            result.message = "Internal server error (cant find user)";
            return result;
        }

        if (!test_user.getPassword().equals(r.password)){
            result.message = "Internal server error (incorrect password)";
            return result;
        }

        result.authToken = UUID.randomUUID().toString();
        result.personID = test_user.personID;
        result.success = AuthTokenDao.updateToken(r.userName, result.authToken);

        return result;
    }

}
