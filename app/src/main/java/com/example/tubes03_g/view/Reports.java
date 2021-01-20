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
import com.example.tubes03_g.model.IncidentDetails;
import com.example.tubes03_g.presenter.IncidentsPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reports extends Fragment implements PostCalculateTask.IMainActivity1, IncidentsPresenter.IMainActivity{

    private Spinner dropdown;
    private ListView reportList;
    private IncidentsPresenter presenter;
    private IncidentListAdapter adapter;
    private FragmentListener listener;
    private Activity activity;
    private ArrayList<IncidentDetails> hasilIncident;
    PostCalculateTask postCalculateTask;

    public Reports(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports, container, false);

        this.dropdown = view.findViewById(R.id.spinner_reports);

        String[] incType = new String[]{"crash", "hazard", "theft", "unconfirmed", "infrastructure_issue", "chop_shop"};

        ArrayAdapter<String> adapterdd = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, incType);
        dropdown.setAdapter(adapterdd);

        hasilIncident = new ArrayList<>();

        this.reportList = view.findViewById(R.id.list_reports);
        this.presenter = new IncidentsPresenter((IncidentsPresenter.IMainActivity)this);

        this.adapter = new IncidentListAdapter((requireActivity()));
        //this.presenter.loadData();
        this.reportList.setAdapter(this.adapter);

        this.reportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IncidentDetails incidentDetailsATM = (IncidentDetails) adapter.getItem(position);
                Log.d("tag", adapter.getItem(position).toString());
                listener.changePage(2);
            }
        });

        this.postCalculateTask = new PostCalculateTask(getContext(), this);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkCapabilities networkInfo = connMgr.getNetworkCapabilities(connMgr.getActiveNetwork());

                String[] incidentType = new String[1];

                switch (position){
                    case 0:
                        //Toast.makeText(parent.getContext(), "crash", Toast.LENGTH_SHORT).show();
                        presenter.refresh();
                        incidentType[0] = incType[0];
                        break;
                    case 1:
                        presenter.refresh();
                        incidentType[0] = incType[1];
                        break;
                    case 2:
                        presenter.refresh();
                        incidentType[0] = incType[2];
                        break;
                    case 3:
                        presenter.refresh();
                        incidentType[0] = "";
                        break;
                    case 4:
                        presenter.refresh();
                        incidentType[0] = incType[4];
                        break;
                    case 5:
                        presenter.refresh();
                        incidentType[0] = incType[5];
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
    public void updateList(List<IncidentDetails> incidentDetailsUP) {
        this.adapter.updateArray(incidentDetailsUP);
    }

    @Override
    public void hasil(IncidentDetails hasilAkses) {
        IncidentDetails tester = hasilAkses;
        presenter.addList(tester.getTitle(), tester.getDescription(), tester.getAddress());
    }


//    @Override
//    public void onClick(View v) {
//        if (v == this.button) {
//            listener.changePage(2);
//        }
//    }
}
