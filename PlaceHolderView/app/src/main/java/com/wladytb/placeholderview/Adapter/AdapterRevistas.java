package com.wladytb.placeholderview.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wladytb.placeholderview.Model.Revistas;
import com.wladytb.placeholderview.R;

import java.util.ArrayList;

public class AdapterRevistas extends ArrayAdapter<Revistas> {

    public AdapterRevistas(Context context, ArrayList<Revistas> datos) {
        super(context, R.layout.revistas, datos);
    }
    public View obtenerView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.revistas, null);
        TextView Name, description;
        ImageView img;
        Name = item.findViewById(R.id.idTitulo);
        description= item.findViewById(R.id.idDescripcion);
        img = item.findViewById(R.id.imgPortada);
        Name.setText(getItem(position).getName());
        description.setText(Html.fromHtml(getItem(position).getDescripccion()));
        Picasso.with(getContext()).load(getItem(position).getPortada()).into(img);
        return(item);
    }
}