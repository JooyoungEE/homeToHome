package com.example.aconmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aconmemo.memo.MemoActivity;

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
        Button btn_memo = findViewById(R.id.button6);
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
        btn_memo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){openMemo();}
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
    public void openMemo(){
        Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
        startActivity(intent);
    }
}