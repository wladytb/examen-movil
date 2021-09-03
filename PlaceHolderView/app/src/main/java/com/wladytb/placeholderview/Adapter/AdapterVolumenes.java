package com.wladytb.placeholderview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wladytb.placeholderview.Model.VolumenesM;
import com.wladytb.placeholderview.R;

import java.util.ArrayList;

public class AdapterVolumenes extends ArrayAdapter<VolumenesM> {

    public AdapterVolumenes(Context context, ArrayList<VolumenesM> datos) {
        super(context, R.layout.volumenes, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.volumenes, null);
        TextView volumen,number,year,datapublisher, doi;
        ImageView img;
        volumen = item.findViewById(R.id.idVolumen);
        number= item.findViewById(R.id.idNumero);
        year = item.findViewById(R.id.idAnio);
        datapublisher= item.findViewById(R.id.idFecha);
        doi = item.findViewById(R.id.idDoi);
        img = item.findViewById(R.id.imgPortada);

        volumen.setText(getItem(position).getVolume());
        number.setText(getItem(position).getNumber());
        year.setText(getItem(position).getYear());
        datapublisher.setText(getItem(position).getDate_published());
        doi.setText(getItem(position).getDoi());
        Picasso.with(getContext()).load(getItem(position).getCover()).into(img);
        return(item);
    }
}
