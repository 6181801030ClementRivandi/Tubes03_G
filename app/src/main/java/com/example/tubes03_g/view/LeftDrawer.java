package com.example.tubes03_g.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tubes03_g.R;

public class LeftDrawer extends Fragment implements View.OnClickListener {

    public LeftDrawer(){}

    Button btnReports;
    Button btnSetting;
    Button btnExit;

    FragmentListener listener;
    DrawerLayout drawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_drawer,container,false);

        this.btnReports = view.findViewById(R.id.drawer_reports);
        btnReports.setOnClickListener(this);

        this.btnSetting = view.findViewById(R.id.drawer_setting);
        btnSetting.setOnClickListener(this);

        this.btnExit = view.findViewById(R.id.drawer_exit);
        btnExit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if ( context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()
                    + "must implement FragmentListener");
        }
    }
    public static LeftDrawer newInstance(String title){
        LeftDrawer fragment = new LeftDrawer();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (v == btnReports){
            this.listener.changePage(1);
        }else if (v == btnSetting){
            this.listener.changePage(3);
        }else{
            this.listener.closeApplication();
        }
    }
}

