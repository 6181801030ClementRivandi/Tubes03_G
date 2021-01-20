package com.example.tubes03_g;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes03_g.model.IncidentDetails;
import com.example.tubes03_g.view.ReportsDetail;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;

public class PostCalculateTask {

    private String BASE_URL = "https://bikewise.org:443/api/v2/incidents?incident_type=";
    private String REG_URL = "https://bikewise.org:443/api/v2/incidents?incident_type=";
    IMainActivity1 ui;
    Context context;
    Gson gson;
    IncidentDetails incidentDetails;

    public PostCalculateTask(Context context, IMainActivity1 ui){
        this.ui = ui;
        this.context = context;
    }

    public void execute(String[] incType) throws JSONException {
        BASE_URL = BASE_URL + incType[0];
        //Log.d("base url", BASE_URL);
        Input input = new Input(incType);
        gson = new Gson();
        String json = gson.toJson(input);
        //Log.d("test", json);
        JSONObject jsonObject = new JSONObject(json);
        callVolley(jsonObject);
        BASE_URL = REG_URL;
    }

    public void callVolley(JSONObject json){
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, json, new Response.Listener<JSONObject>() {

            private String title, description, address;
            @Override
            public void onResponse(JSONObject jsonObject) {

                ArrayList<IncidentDetails> hasil = new ArrayList<>();

                try {
                    for ( int x = 0; x < jsonObject.getJSONArray("incidents").length(); x++){
                        title = (String)jsonObject.getJSONArray("incidents").getJSONObject(x).get("title");
                        description = (String)jsonObject.getJSONArray("incidents").getJSONObject(x).get("description");
                        address = (String)jsonObject.getJSONArray("incidents").getJSONObject(x).get("address");
                        incidentDetails = new IncidentDetails(0, this.title, this.description, this.address);
                        //Log.d("tester", incidentDetails.getTitle());
                        hasil.add(incidentDetails);
                        processResult(incidentDetails);
                    }

                }catch ( JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Log.d("tester", jsonObjectRequest.toString());
        requestQueue.add(jsonObjectRequest);
    }

    public void processResult(IncidentDetails resHasil){
        this.ui.hasil(resHasil);
    }

    public interface IMainActivity1{
        void hasil(IncidentDetails hasilAkses);
    }
}
