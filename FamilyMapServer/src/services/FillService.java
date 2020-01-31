package services;

import requests.FillRequest;
import responses.FillResult;

public class FillService {

    public static FillResult fill(FillRequest r){
        FillResult result = new FillResult();

        GenerateDataService.generateGenerations(r.username, r.generations);

        result.message = "Successully added 0 persons and " + r.generations + " events to the database.";
        result.success = true;

        return result;
    }

}
