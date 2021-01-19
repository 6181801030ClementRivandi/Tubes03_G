package com.example.tubes03_g;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

public class PostCalculateTask {

    final String BASE_URL = "https://bikewise.org:443/api/v2/incidents?";
    IMainActivity ui;
    Context context;
    Gson gson;

    public PostCalculateTask(Context context, IMainActivity ui){
        this.ui = ui;
        this.context = context;
    }

    public void execute(String incType) throws JSONException {
        Input input = new Input(incType);
        gson = new Gson();
        String json = gson.toJson(input);
        JSONObject jsonObject = new JSONObject(json);
        callVolley(jsonObject);
    }

    public void callVolley(JSONObject json){
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String res = jsonObject.toString();
                processResult(res);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void processResult(String json){
        Result result = gson.fromJson(json,Result.class);
        this.ui.setHasil(result);
    }


    public interface IMainActivity{
        void setHasil(Result result);
    }
}
