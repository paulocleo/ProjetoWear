package com.example.pc.soccerwear;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 23/10/2017.
 */

public class JsonUtil {

    public static List<Time> fromJson(String json) throws JSONException {

        List<Time> lista = new ArrayList<>();

        JSONObject listaObject = new JSONObject(json);

        JSONArray codigosTimesArray = listaObject.names();
        for (int i = 0; i < codigosTimesArray.length(); i++){

            JSONObject timeObject = listaObject.getJSONObject(codigosTimesArray.get(i).toString());

            int idTime        = timeObject.getInt("id");
            String nomeTime   = timeObject.getString("nome");
            String abreviacao =  timeObject.getString("abreviacao");

            JSONObject escudoObject = timeObject.getJSONObject("escudos");
            String caminhoEscudo = escudoObject.getString("30x30");
            if(!caminhoEscudo.isEmpty())
            {
                lista.add(new Time(idTime, nomeTime, abreviacao, caminhoEscudo));
            }

            Log.d("PCAC",nomeTime);
        }

        return lista;
    }
}
