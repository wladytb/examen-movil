package com.wladytb.placeholderview.Holder;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.wladytb.placeholderview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@NonReusable
@Layout(R.layout.articulos)
public class HolderArticulo {
    @View(R.id.idTituloArticulo)
    TextView TextTitulo;
    @View(R.id.idAutores)
    TextView TextAutores;
    Context mcontext;
    JSONObject jsonObject;


    @Click(R.id.idBtnDescarga)
    public void clickItemVolumen() {
        try {
            JSONArray js = jsonObject.getJSONArray("galeys");
            for (int i = 0; i < js.length(); i++) {
                String url = js.getJSONObject(i).getString("UrlViewGalley");
                DescargarPDF(url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void DescargarPDF(String urllink) {
        try {

            URL url = new URL(urllink);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String Path = Environment.getExternalStorageDirectory() + "/download/";
            Log.v("PdfManager", "PATH: " + Path);
            File file = new File(Path);
            file.mkdirs();
            FileOutputStream fos = new FileOutputStream("tusitio.pdf");
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[702];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            Log.d("PdfManager", "Error: " + e);
        }
        Log.v("PdfManager", "Check: ");
    }


    public HolderArticulo(Context mcontext, JSONObject jsonObject) {
        this.mcontext = mcontext;
        this.jsonObject = jsonObject;
    }

    @Resolve
    public void onResolved() {

        try {
            this.TextTitulo.setText(jsonObject.getString("title"));
            this.TextAutores.setText(autoresResult(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String autoresResult(JSONObject object) {
        String Autores = "";

        try {
            if (jsonObject.getJSONArray("authors").length() > 1)
                Autores = "Autores: ";
            else
                Autores = "Autor: ";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("authors");
            for (int i = 0; i < jsonArray.length(); i++) {
                Autores += jsonArray.getJSONObject(i).getString("nombres") + ", ";
            }
            Autores = Autores.substring(0, Autores.length() - 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Autores;
    }
}
