package com.example.sistempakarhydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.*;
import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

public class DummyInput extends AppCompatActivity implements AntaresHTTPAPI.OnResponseListener {

    private String TAG = "ANTARES-API";
    private AntaresHTTPAPI antaresAPIHTTP;
    EditText in_ph,in_suhu,in_ec,in_cahaya;
    Button sub_mit;
    int val_ph,val_sh,val_ec,val_cahaya;
    String temp, jsonDummy;

    String acc_key = "a226e7d7a5e8b3c7:1bcbaa4dce2ab86c";
    String app_name = "HidroponikTelkom2020";
    String device_name = "apaya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_input);
        in_ph = findViewById(R.id.in_ph);
        in_suhu = findViewById(R.id.in_suhu);
        in_ec = findViewById(R.id.in_ec);
        in_cahaya = findViewById(R.id.in_cahaya);
        sub_mit = findViewById(R.id.sub_mit);

        antaresAPIHTTP = new AntaresHTTPAPI();
        antaresAPIHTTP.addListener(this);

        sub_mit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get edittext
                temp = in_ph.getText().toString();
                if(!"".equals(temp)){
                    val_ph = Integer.parseInt(temp);
                    Log.i("ph" ," " +val_ph);
                }
                temp = in_ec.getText().toString();
                if(!"".equals(temp)){
                    val_ec = Integer.parseInt(temp);
                    Log.i("EC" ," " +val_ec);
                }
                temp = in_suhu.getText().toString();
                if(!"".equals(temp)){
                    val_sh = Integer.parseInt(temp);
                    Log.i("EC" ," " +val_sh);
                }
                temp = in_cahaya.getText().toString();
                if(!"".equals(temp)){
                    val_cahaya = Integer.parseInt(temp);
                    Log.i("EC" ," " +val_cahaya);
                }
                try {
                    jsonDummy = new JSONObject()
                            .put("pH",""+val_ph)
                            .put("EC",""+val_ec)
                            .put("Temp",""+val_sh)
                            .put("Lux",""+val_cahaya).toString();
                    jsonDummy = jsonDummy.replace("\"","\\\"");
                    Log.d("String",jsonDummy);
                    String a = jsonDummy;
                    antaresAPIHTTP.storeDataofDevice(1,acc_key,app_name,device_name,a);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResponse(AntaresResponse antaresResponse) {

    }
}
