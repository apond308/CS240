package services;


import dao.AuthTokenDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;

import responses.ClearResult;

public class ClearService {

    public static ClearResult clear(){
        ClearResult result = new ClearResult();

        UserDao.clear();
        PersonDao.clear();
        EventDao.clear();
        AuthTokenDao.clear();

        result.message = "Clear succeeded.";
        result.success = true;

        return result;
    }

}
