package com.example.sistempakarhydroponics;

import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

public class CekTanaman extends AppCompatActivity implements AntaresHTTPAPI.OnResponseListener {


    private String TAG = "ANTARES-API";
    private AntaresHTTPAPI antaresAPIHTTP;
    private String dataDevice,value,key;
    String lux,hum,temp;
    int keyin;
    TextView TextLux,Suhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_tanaman);

        TextLux = findViewById(R.id.lux);
        Suhu = findViewById(R.id.suhu);

        antaresAPIHTTP = new AntaresHTTPAPI();
        antaresAPIHTTP.addListener(this);
        antaresAPIHTTP.getLatestDataofDevice("5a185cedc6ad749b:edb0b82be780ba6d","expertsystem","TemperatureSensor");
    }

    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        Log.d(TAG,Integer.toString(antaresResponse.getRequestCode()));
        if(antaresResponse.getRequestCode()==0) {
            try {
                JSONObject body = new JSONObject(antaresResponse.getBody());
                dataDevice = body.getJSONObject("m2m:cin").getString("con");
                JSONObject value = new JSONObject(dataDevice);
                temp = value.getString("temp");
                hum  = value.getString("humidity");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Suhu.setText(hum);
                    }
                });
                Log.i("data",temp);
                Log.i("data",hum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
