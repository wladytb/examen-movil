package com.wladytb.placeholderview.Holder;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;
import com.wladytb.placeholderview.Articulos;
import com.wladytb.placeholderview.Model.VolumenesM;
import com.wladytb.placeholderview.R;
import com.wladytb.placeholderview.Volumenes;

@NonReusable
@Layout(R.layout.volumeneslist)
public class HolderVolumen {
    @View(R.id.idListVolumenes)
    PlaceHolderView PlaceHolderView;
    @View(R.id.imgPortada)
    ImageView Importada;
    @View(R.id.idVolumen)
    TextView TextVolumen;
    @View(R.id.idNumero)
    TextView TxtNumero;
    @View(R.id.idAnio)
    TextView TxtAnio;
    @View(R.id.idFecha)
    TextView TxtFecha;
    @View(R.id.idDoi)
    TextView TxtDoi;

    @Click(R.id.listaVolumen)
    public void clickItemVolumen(){
        Intent intent = new Intent(mcontext, Articulos.class);
        intent.putExtra("idVolumen", volumen.getIssue_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        mcontext.startActivity(intent);
    }

    VolumenesM volumen;
    Context mcontext;

    public HolderVolumen(Context context,VolumenesM volumenes) {
        this.mcontext = context;
        this.volumen = volumenes;
    }
    @Resolve
    public void onResolved() {
        this.TextVolumen.setText(volumen.getVolume());
        this.TxtNumero.setText(volumen.getNumber());
        this.TxtAnio.setText(volumen.getYear());
        this.TxtFecha.setText(volumen.getDate_published());
        this.TxtDoi.setText(volumen.getDoi());
        Picasso.with(mcontext).load(volumen.getCover()).into(Importada);
    }
}
