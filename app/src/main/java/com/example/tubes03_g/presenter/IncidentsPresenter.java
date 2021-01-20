package com.example.tubes03_g.presenter;

import com.example.tubes03_g.model.IncidentDetails;

import java.util.ArrayList;
import java.util.List;

public class IncidentsPresenter {

    public interface IMainActivity{
        void updateList(List<IncidentDetails> incidentDetailsUP);
    }

    protected List<IncidentDetails> incidents;
    protected IMainActivity ui;

    public IncidentsPresenter(IMainActivity ui){
        this.incidents = new ArrayList<>();
        this.ui = ui;
    }

    public void loadData(){
        this.ui.updateList(this.incidents);
    }

    public void refresh(){
        this.incidents.clear();
    }

    public int countItem(){
        return incidents.size();
    }

    public void addList(String title, String descrition, String address){
        this.incidents.add(new IncidentDetails(+1,title,descrition, address));
        this.ui.updateList(this.incidents);
    }

}
