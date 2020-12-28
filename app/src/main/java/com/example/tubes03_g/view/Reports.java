package com.example.tubes03_g.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes03_g.R;

public class Reports extends Fragment {
    private ListView reportList;
    private TextView tv; //tv sementara
    private FragmentListener listener;

    public Reports(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports, container, false);

        this.tv = view.findViewById(R.id.tv_sementara);
        this.reportList = view.findViewById(R.id.list_reports);

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

//    @Override
//    public void onClick(View v) {
//        if (v == this.button) {
//            listener.changePage(2);
//        }
//    }
}
