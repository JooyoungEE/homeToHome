package com.example.hometohome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btn_stopWatch = findViewById(R.id.button);
        Button btn_homeToHome = findViewById(R.id.button2);
        Button btn_listView = findViewById(R.id.button3);
        Button btn_sharedPreference = findViewById(R.id.button4);
        Button btn_sharedPreference2 = findViewById(R.id.button5);
        btn_stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openStopWatchActivity();
            }
        });
        btn_homeToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeToHome();
            }
        });
        btn_listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openListView(); }
        });
        btn_sharedPreference.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openSharedPreference();}
        });
        btn_sharedPreference2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSharedPreference2();}
        });
    }

    public void openStopWatchActivity() {
        Intent intent = new Intent(MenuActivity.this, StopWatchActivity.class);
        startActivity(intent);
    }
    public void openHomeToHome(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void openListView(){
        Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
        startActivity(intent);
    }
    public void openSharedPreference(){
        Intent intent = new Intent(getApplicationContext(), SharedPreferenceActivity.class);
        startActivity(intent);
    }
    public void openSharedPreference2(){
        Intent intent = new Intent(getApplicationContext(), SharedPreferenceActivity2.class);
        startActivity(intent);
    }
}