package com.example.pc.soccerwear;


import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, DataApi.DataListener, CarrierMessagingService.ResultCallback<DataApi.DataItemResult>, View.OnClickListener {

    private GoogleApiClient mGoogleApiClient;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build();

        findViewById(R.id.btSend).setOnClickListener(this);

        if(getIntent() != null){
            Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
            if(remoteInput != null){
                TextView tv = (TextView) findViewById(R.id.txtResposta);
                tv.setText( RemoteInput.getResultsFromIntent(getIntent()).getCharSequence("resultKey") );
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mGoogleApiClient != null){
            mGoogleApiClient.disconnect();
            Wearable.DataApi.removeListener(mGoogleApiClient,this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(mGoogleApiClient,this);
    }


    @Override
    public void onReceiveResult(DataApi.DataItemResult dataItemResult) throws RemoteException {
        if(dataItemResult.getStatus().isSuccess()){
            Log.d("PCAC", "Enviado com sucesso");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btSend:
                sendDataToWearable();
                break;
        }
    }

    private void sendDataToWearable() {
        PutDataMapRequest dataMap = PutDataMapRequest.create("/my_path");

        dataMap.getDataMap().putString(Constants.KEY_NAME, "time_fotebol");
        dataMap.getDataMap().putString(Constants.KEY_POSITION, "posicao");
        dataMap.getDataMap().putLong("time", new Date().getTime());

        PutDataRequest request = dataMap.asPutDataRequest();

        Wearable.DataApi.putDataItem(mGoogleApiClient,request).setResultCallback((ResultCallback<? super DataApi.DataItemResult>) this);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {

    }
}
