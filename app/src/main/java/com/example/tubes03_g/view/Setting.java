package com.example.tubes03_g.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.tubes03_g.R;

public class Setting extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Switch switchTheme;
    private int currentMode;
    private SharedPreferences sharedPref;

    public Setting() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);

        switchTheme = view.findViewById(R.id.swtich_theme);
        switchTheme.setOnCheckedChangeListener(this);

        loadData();

        return view;
    }

    public static Setting newInstance() {
        Setting fragment = new Setting();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        saveData();
    }

    public void saveData() {
        sharedPref = getActivity().getSharedPreferences("SETTING", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("IsSwitch", switchTheme.isChecked());
        editor.apply();
    }

    public void loadData() {
        currentMode = AppCompatDelegate.getDefaultNightMode();
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            switchTheme.setChecked(true);
        } else {
            switchTheme.setChecked(false);
        }

        sharedPref = getActivity().getSharedPreferences("SETTING", Context.MODE_PRIVATE);
        boolean savedBoolean = sharedPref.getBoolean("IsSwitch", false);
        switchTheme.setChecked(savedBoolean);
    }

}
