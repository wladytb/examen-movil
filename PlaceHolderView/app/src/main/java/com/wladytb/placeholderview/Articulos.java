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
import com.wladytb.placeholderview.Holder.HolderArticulo;

import org.json.JSONArray;
import org.json.JSONException;

public class Articulos extends AppCompatActivity{
    String id ;
    PlaceHolderView listArticulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);
        id = getIntent().getExtras().getString("idVolumen");
        listArticulos = findViewById(R.id.listArticulos);
       consumirGoolgeVolley();
    }
    public void consumirGoolgeVolley() {
        System.out.println("entra----------------------------------------------------");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET, "https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        listArticulos.addView(new HolderArticulo(getApplicationContext(),response.getJSONObject(i)));
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