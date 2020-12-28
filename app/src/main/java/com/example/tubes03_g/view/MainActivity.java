package com.example.tubes03_g.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes03_g.R;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private Home home;
    private MapsFragment mapsFragment;
    private Reports reports;
    private Setting setting;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.home = Home.newInstance();
        this.mapsFragment = MapsFragment.newInstance();
        this.reports = Reports.newInstance();
        this.setting = Setting.newInstance();

        this.fragmentManager = this.getSupportFragmentManager();
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        this.changePage(3);
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if (page == 1) {
            ft.replace(R.id.fragment_container, this.mapsFragment);
        } else if (page == 2) {
            ft.replace(R.id.fragment_container, this.reports).addToBackStack(null);
        } else if (page == 3) {
            ft.replace(R.id.fragment_container, this.setting).addToBackStack(null);
        }
//        else if (page == 4) {
//            ft.replace(R.id.fragment_container, this.settingFragment).addToBackStack(null);
//        }

        this.drawer.close();
        this.ft.commit();
    }

    @Override
    public void closeApplication() {
        moveTaskToBack(true);
        finish();
    }
}