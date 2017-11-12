package com.example.pc.soccerwear;

import android.support.wearable.view.WearableRecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PC on 11/11/2017.
 */

public class itemViewHolder extends WearableRecyclerView.ViewHolder{

    TextView txtTexto;
    ImageView imgEscudo;

    public itemViewHolder(View itemView) {
        super(itemView);

        imgEscudo = (ImageView) itemView.findViewById(R.id.imagemV);
        txtTexto = (TextView) itemView.findViewById(R.id.tvItem);
    }
}
