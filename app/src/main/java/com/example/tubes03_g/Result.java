package com.example.tubes03_g;

import com.example.tubes03_g.model.IncidentDetails;


public class Result {

    IncidentDetails incidentDetails;

    public Result(IncidentDetails incidentDetailsRes) {
        this.incidentDetails = incidentDetailsRes;
    }

    public IncidentDetails getIncidentDetails() {
        return incidentDetails;
    }

    public void setIncidentDetails(IncidentDetails incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

}
