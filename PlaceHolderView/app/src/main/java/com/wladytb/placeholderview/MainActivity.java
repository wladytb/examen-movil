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
import com.wladytb.placeholderview.Holder.RevistasHolder;
import com.wladytb.placeholderview.Model.Revistas;

import org.json.JSONArray;
import org.json.JSONException;
public class MainActivity extends AppCompatActivity {

    PlaceHolderView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        listView = findViewById(R.id.idListaRevistas);
       consumirGoolgeVolley();
    }

    public void consumirGoolgeVolley() {
        System.out.println("entra----------------------------------------------------");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET, "https://revistas.uteq.edu.ec/ws/journals.php",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Revistas revistas = new Revistas();
                        revistas.setName(response.getJSONObject(i).getString("name"));
                        revistas.setJournal_id(response.getJSONObject(i).getString("journal_id"));
                        revistas.setDescripccion(response.getJSONObject(i).getString("description"));
                        revistas.setAbbreviation(response.getJSONObject(i).getString("abbreviation"));
                        revistas.setJournalThumbnail(response.getJSONObject(i).getString("journalThumbnail"));
                        revistas.setPortada(response.getJSONObject(i).getString("portada"));
                        listView.addView(new RevistasHolder(getApplicationContext(), revistas));
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