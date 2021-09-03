package com.wladytb.placeholderview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.PlaceHolderView;
import com.wladytb.placeholderview.Holder.HolderVolumen;
import com.wladytb.placeholderview.Model.VolumenesM;

import org.json.JSONArray;
import org.json.JSONException;

public class Volumenes extends AppCompatActivity {
    String id = "";
    PlaceHolderView listViewVolumenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumenes);
        getSupportActionBar().hide();
        id = (String) getIntent().getExtras().get("idRevista");
        listViewVolumenes = findViewById(R.id.idListVolumenes);
        consumirGoolgeVolley();
    }
    public void consumirGoolgeVolley() {
        System.out.println("entra----------------------------------------------------");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET, "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        VolumenesM volumen= new VolumenesM();
                        volumen.setVolume(response.getJSONObject(i).getString("volume"));
                        volumen.setNumber(response.getJSONObject(i).getString("number"));
                        volumen.setDate_published(response.getJSONObject(i).getString("date_published"));
                        volumen.setDoi(response.getJSONObject(i).getString("doi"));
                        volumen.setIssue_id(response.getJSONObject(i).getString("issue_id"));
                        volumen.setTitle(response.getJSONObject(i).getString("title"));
                        volumen.setYear(response.getJSONObject(i).getString("year"));
                        volumen.setCover(response.getJSONObject(i).getString("cover"));
                        listViewVolumenes.addView(new HolderVolumen(getApplicationContext(),volumen));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        requestQueue.add(jsArrayRequest);
    }
}