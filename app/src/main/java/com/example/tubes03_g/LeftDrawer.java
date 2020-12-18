package com.example.tubes03_g;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class LeftDrawer extends Fragment implements View.OnClickListener {

    public LeftDrawer(){}

//    Button button1;
//    Button button2;
//    Button button3;
    FragmentListener listener;
    DrawerLayout drawer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_drawer,container,false);

//        this.button1 = view.findViewById(R.id.home);
//        button1.setOnClickListener(this);
//
//        this.button2 = view.findViewById(R.id.pg2);
//        button2.setOnClickListener(this);
//
//        this.button3 = view.findViewById(R.id.exit);
//        button3.setOnClickListener(this);

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
//        if (v == button1){
//            this.listener.changePage(1);
//
//        }else if ( v == button2){
//            this.listener.changePage(2);
//        }else{
//            this.listener.closeApplication();
//        }
    }
}

