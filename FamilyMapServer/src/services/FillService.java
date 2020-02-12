package services;

import dao.EventDao;
import requests.FillRequest;
import responses.FillResult;

public class FillService {

    /**
     *
     * @param r
     * @return
     */
    public static FillResult fill(FillRequest r){
        FillResult result = new FillResult();

        EventDao.deleteUserEvents(r.username);

        int generations_added = GenerateDataService.generateGenerations(r.username, r.generations);

        result.message = "Successfully added " + generations_added + " persons and " + (generations_added*3-1) + " events to the database.";
        result.success = true;

        return result;
    }

}
