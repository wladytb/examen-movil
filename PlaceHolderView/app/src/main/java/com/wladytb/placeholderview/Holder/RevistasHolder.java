package com.wladytb.placeholderview.Holder;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;
import com.wladytb.placeholderview.Model.Revistas;
import com.wladytb.placeholderview.R;
import com.wladytb.placeholderview.Volumenes;

@NonReusable
@Layout(R.layout.revistas)
public class RevistasHolder {
    @View(R.id.idListaRevistas)
    PlaceHolderView PlaceHolderView;
    @View(R.id.imgPortada)
    ImageView Importada;
    @View(R.id.idDescripcion)
    TextView TextDescripcion;
    @View(R.id.idTitulo)
    TextView TxtName;

    Revistas revista = new Revistas();

    @Click(R.id.liPrincipal)
    public void clickRevista() {
        Intent intent = new Intent(mContext, Volumenes.class);
        intent.putExtra("idRevista", revista.getJournal_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        mContext.startActivity(intent);
    }


    private Context mContext;

    public RevistasHolder(Context context, Revistas revistas) {
        this.revista = revistas;
        this.mContext = context;
    }
    @Resolve
    public void onResolved() {
        this.TxtName.setText(revista.getName());
        this.TextDescripcion.setText(Html.fromHtml(revista.getDescripccion()));
        Picasso.with(mContext).load(revista.getPortada()).into(Importada);
    }
}

