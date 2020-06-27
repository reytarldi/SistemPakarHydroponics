package com.example.sistempakarhydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SignUp extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signup = findViewById(R.id.btn_signup);
        btn_back = findViewById(R.id.btn_back);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignupdone = new Intent(SignUp.this,SignUpDone.class);
                startActivity(gotosignupdone);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtogetstarted = new Intent(SignUp.this,GetStarted.class);
                startActivity(backtogetstarted);
            }
        });
    }
}
