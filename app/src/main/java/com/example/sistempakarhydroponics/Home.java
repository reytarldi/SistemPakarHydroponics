package com.example.sistempakarhydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageView pilih_tanaman,cek_tanaman,setting_antares,dummy;
    TextView in_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        in_database = findViewById(R.id.in_database);
        dummy = findViewById(R.id.dummy);
        pilih_tanaman = findViewById(R.id.pilih_tanaman);
        cek_tanaman = findViewById(R.id.cek_tanaman);
        setting_antares = findViewById(R.id.setting_antares);

        in_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoindatabase = new Intent(Home.this,InputDatabase.class);
                startActivity(gotoindatabase);
            }
        });

        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodummy = new Intent(Home.this,DummyInput.class);
                startActivity(gotodummy);
            }
        });

        pilih_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopilihtanaman = new Intent(Home.this,PilihTanaman.class);
                startActivity(gotopilihtanaman);
            }
        });

        cek_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocektanaman = new Intent(Home.this, CekTanaman.class);
                startActivity(gotocektanaman);
            }
        });

        setting_antares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosettingantares = new Intent(Home.this,SettingAntares.class);
                startActivity(gotosettingantares);
            }
        });
    }
}
