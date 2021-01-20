package com.example.tubes03_g.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tubes03_g.PostCalculateTask;
import com.example.tubes03_g.R;
import com.example.tubes03_g.model.IncidentDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ReportsDetail extends Fragment implements View.OnClickListener, PostCalculateTask.IMainActivity1 {
    private FragmentListener listener;
    Button btnViewReport;
    ImageView ivDetail;
    TextView tvTitle, tvType, tvDate, tvLocation, tvDesc;
    private IncidentDetails incidentDetails;

    public ReportsDetail(){
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_detail, container, false);

        this.ivDetail = view.findViewById(R.id.iv_detail);
        this.tvDate = view.findViewById(R.id.report_date);
        this.tvDesc = view.findViewById(R.id.report_description);
        this.tvLocation = view.findViewById(R.id.report_location);
        this.tvTitle = view.findViewById(R.id.report_title);
        this.tvType = view.findViewById(R.id.report_type);

        this.btnViewReport = view.findViewById(R.id.btn_viewreport);
        this.btnViewReport.setOnClickListener(this);

        Bundle bundle = getArguments();
        if ( bundle != null){
            this.incidentDetails = bundle.getParcelable("incidentDetails");
            Log.d("cobabos", incidentDetails.getTitle());
//            this.tvTitle.setText(incidentDetails.getTitle());
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
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

    public static ReportsDetail newInstance() {
        ReportsDetail fragment = new ReportsDetail();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (btnViewReport == v) {
//            Logger.d("hello"); coba logger
            this.listener.changePage(1);
        }
    }

    @Override
    public void hasil(IncidentDetails hasilAkses) {
        this.tvTitle.setText(hasilAkses.getTitle());
        Log.d("hasil get", hasilAkses.getTitle());
        Log.d("hasil", tvTitle.getText().toString());
    }
}