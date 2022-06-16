package com.mytech.smmpanel.Admins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mytech.smmpanel.MainActivity;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.SharedPrefrence.PrefManager;

public class SplashScreen extends AppCompatActivity {

    Button btnamin,btnuser;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefManager=new PrefManager(this);
        btnamin=findViewById(R.id.btn1);
        btnuser=findViewById(R.id.userpanel);

        btnamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AdminMain.class);
                intent.putExtra("key","admin");
                startActivity(intent);
                prefManager.setToken_Email("admin");
            }
        });

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("key","user");
                startActivity(intent);
                prefManager.setToken_Email("user");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (prefManager.getToken_Email().equals("admin")){
            Intent intent=new Intent(getApplicationContext(), AdminMain.class);
            intent.putExtra("key","admin");
            startActivity(intent);
        }else if (prefManager.getToken_Email().equals("user")){
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("key","user");
            startActivity(intent);
        }
    }
}