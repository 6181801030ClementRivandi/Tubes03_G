package com.example.tubes03_g.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tubes03_g.R;
import com.example.tubes03_g.model.IncidentDetails;

import java.util.ArrayList;
import java.util.List;

public class IncidentListAdapter extends BaseAdapter {

    private List<IncidentDetails> incidentDetailsList;
    private Activity activity;

    public IncidentListAdapter(Activity activity) {
        this.incidentDetailsList = new ArrayList<IncidentDetails>();
        this.activity = activity;
    }

    private class ViewHolder{
        protected TextView title;
        public ViewHolder(View view) {
            this.title = view.findViewById(R.id.incident_title);
        }
    }

    public void addLine(IncidentDetails incident){
        this.incidentDetailsList.add(incident);
        this.notifyDataSetChanged();
    }

    public void updateArray(List<IncidentDetails> incidents){
        this.incidentDetailsList = incidents;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.incidentDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return incidentDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View convertView = LayoutInflater.from(this.activity).inflate(R.layout.item_list_incident, parent, false);
        IncidentDetails currentIncident = (IncidentDetails) this.getItem(i);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.title.setText(currentIncident.getTitle());
        return convertView;
    }
}
