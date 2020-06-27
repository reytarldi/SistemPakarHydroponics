package com.example.sistempakarhydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class InputDatabase extends AppCompatActivity implements AntaresHTTPAPI.OnResponseListener{

    EditText db_url,db_ph_bwh,db_ph_ats,db_ec_bwh,db_ec_ats,db_tanaman;
    Button db_submit;

    String val_ph_bwh,val_ph_ats,val_ec_bwh,val_ec_ats,val_url,val_tanaman;
    String storejsonDB,getjsonDB,parseJSON;

    final String accesskey = "a226e7d7a5e8b3c7:1bcbaa4dce2ab86c";
    final String projectname = "HidroponikTelkom2020";
    final String devicename = "apaya";

    private String TAG = "ANTARES-API";
    private AntaresHTTPAPI antaresAPIHTTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_database);

        db_tanaman = findViewById(R.id.db_tanaman);
        db_url = findViewById(R.id.db_url);
        db_ph_bwh = findViewById(R.id.db_ph_bwh);
        db_ph_ats = findViewById(R.id.db_ph_ats);
        db_ec_bwh = findViewById(R.id.db_ec_bwh);
        db_ec_ats = findViewById(R.id.db_ec_ats);
        db_submit = findViewById(R.id.db_submit);

        antaresAPIHTTP = new AntaresHTTPAPI();
        antaresAPIHTTP.addListener(this);

        db_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antaresAPIHTTP.getLatestDataofDevice(accesskey,projectname,devicename);
            }
        });
    }

    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        if(antaresResponse.getRequestCode()==0){
            try {
                JSONObject body = new JSONObject(antaresResponse.getBody());
                getjsonDB = body.getJSONObject("m2m:cin").getString("con");
                JSONObject obj = new JSONObject(getjsonDB);
                parseJSON = obj.toString();
                Log.d("cek",parseJSON);
                storedata();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void storedata(){
        val_tanaman = db_tanaman.getText().toString();
        val_url = db_url.getText().toString();
        val_ph_bwh = db_ph_bwh.getText().toString();
        val_ph_ats = db_ph_ats.getText().toString();
        val_ec_bwh = db_ec_bwh.getText().toString();
        val_ec_ats = db_ec_ats.getText().toString();

        try {
            storejsonDB = new JSONObject()
                    .put(val_tanaman,new JSONObject()
                        .put("image_url",val_url)
                        .put("pH_bwh",val_ph_bwh)
                        .put("ph_ats",val_ph_ats)
                        .put("ec_bwh",val_ec_bwh)
                        .put("ec_ats",val_ec_ats))
                    .toString();
            Log.d("well",storejsonDB);
            Log.d("well",parseJSON);
    // hapus awal dan akhir
            String str1 = parseJSON.substring(0, parseJSON.length()-1);
            String str2 = storejsonDB.substring(1);
            Log.d("concat",str1);
            Log.d("concat",str2);
    //combine string
            String combined = str1 + "," + str2;
            Log.d("combined",combined);
            combined = combined.replace("\"","\\\"");
            Log.d("parse",combined);
    // upload string ke antares
        antaresAPIHTTP.storeDataofDevice(1,accesskey,projectname,devicename,combined);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
