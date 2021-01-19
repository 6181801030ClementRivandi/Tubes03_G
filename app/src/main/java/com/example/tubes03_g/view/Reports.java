package com.example.tubes03_g.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.tubes03_g.PostCalculateTask;
import com.example.tubes03_g.R;
import com.example.tubes03_g.Result;

import org.json.JSONException;

public class Reports extends Fragment implements PostCalculateTask.IMainActivity{

    private Spinner dropdown;
    private ListView reportList;
    private IncidentListAdapter adapter;
    private FragmentListener listener;
    private Activity activity;
    PostCalculateTask postCalculateTask;

    public Reports(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports, container, false);


        this.dropdown = view.findViewById(R.id.spinner_reports);

        String[] incType = new String[]{"crash", "hazard", "theft", "unconfirmed", "infrastructure_issue", "chop_shop"};

        ArrayAdapter<String> adapterdd = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, incType);
        dropdown.setAdapter(adapterdd);

        this.reportList = view.findViewById(R.id.list_reports);
        activity = this.getActivity();

        this.adapter = new IncidentListAdapter(activity);

        this.postCalculateTask = new PostCalculateTask(getContext(), this);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkCapabilities networkInfo = connMgr.getNetworkCapabilities(connMgr.getActiveNetwork());

                String incidentType = "";

                switch (position){
                    case 0:
                        //Toast.makeText(parent.getContext(), "crash", Toast.LENGTH_SHORT).show();
                        incidentType = "incident_type=crash";
                        break;

                }

                if ( networkInfo != null){
                    try{
                        postCalculateTask.execute(incidentType);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " Must Implement Fragment Listener");
        }
    }

    public static Reports newInstance(){
        Reports fragment = new Reports();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setHasil(Result result) {
        String hasil = result.getResult();
    }


//    @Override
//    public void onClick(View v) {
//        if (v == this.button) {
//            listener.changePage(2);
//        }
//    }
}
