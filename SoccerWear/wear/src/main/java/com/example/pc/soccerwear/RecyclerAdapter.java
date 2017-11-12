package com.example.pc.soccerwear;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.WearableRecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 11/11/2017.
 */

public class RecyclerAdapter extends WearableRecyclerView.Adapter<itemViewHolder> {

    String[] data = new String[]{"Time 1","Time 2","Time 3", "Time 4", "Time 5", "Time 6", "Time 7"};
    Context mContext;
    List<Time> lista;

    public RecyclerAdapter(List<Time> listaTime) {

        lista = new ArrayList<>();
        lista = listaTime;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, null);

        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {

        if(lista.size() > 0){
            final Time time_item =  lista.get(position);
            holder.txtTexto.setText(time_item.getNome());

            try {
                InputStream is = (InputStream) new URL(time_item.getCaminhoEscudo()).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                holder.imgEscudo.setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("PCAC","Time Selecionado: " +time_item.getNome());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
