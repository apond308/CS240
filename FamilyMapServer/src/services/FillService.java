package services;

import dao.EventDao;
import requests.FillRequest;
import responses.FillResult;
import server.GenerateData;

public class FillService {

    /**
     *
     * @param r request data
     * @return result of service
     */
    public static FillResult fill(FillRequest r){
        FillResult result = new FillResult();

        if (r.generations < 1)
        {
            result.success = false;
            return result;
        }

        EventDao.deleteUserEvents(r.username);

        int generations_added = GenerateData.generateGenerations(r.username, r.generations);

        result.message = "Successfully added " + generations_added + " persons and " + (generations_added*3-1) + " events to the database.";
        result.success = true;

        return result;
    }

}
