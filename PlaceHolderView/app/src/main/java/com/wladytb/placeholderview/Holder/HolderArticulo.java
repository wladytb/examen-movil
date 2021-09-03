package com.wladytb.placeholderview.Holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.wladytb.placeholderview.Model.Articulo;
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
    Articulo articulo;
    JSONArray jsonArrayAutores;
    JSONObject jsonObject;

    @Click(R.id.idBtnDescarga)
    public void descargar() {
        String url = articulo.getDoi();
        System.out.println(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        mcontext.startActivity(intent);
    }

    @Click(R.id.idBtnHtml)
    public void irToPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://revistas.uteq.edu.ec/index.php/cyt/article/view/"+articulo.getSubmission_id()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        mcontext.startActivity(intent);
    }
    Context context;
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
            FileOutputStream fos = new FileOutputStream("articulo.pdf");
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


    public HolderArticulo(Context mcontext, Articulo articulo, JSONObject jsonGaleys, JSONArray jsonArrayAutores) {
        this.mcontext = mcontext;
        this.articulo = articulo;
        this.jsonArrayAutores = jsonArrayAutores;
        this.jsonObject = jsonGaleys;
    }

    @Resolve
    public void onResolved() {
        this.TextTitulo.setText(articulo.getTitle());
        this.TextAutores.setText(autoresResult(jsonArrayAutores));
    }

    private String autoresResult(JSONArray jsonArray) {
        String Autores = "";

        if (jsonArray.length() > 1)
            Autores = "Autores: ";
        else
            Autores = "Autor: ";

        try {
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
