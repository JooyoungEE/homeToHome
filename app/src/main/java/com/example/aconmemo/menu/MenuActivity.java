package com.example.aconmemo.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aconmemo.*;
import com.example.aconmemo.memo.*;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list = new ArrayList<>();
        list.add("StopWatch");
        list.add("HomeToHome");
        list.add("ListView");
        list.add("SharedPreference");
        list.add("SharedPreference2");
        list.add("Memo");
        list.add("Memo_SQLite");

        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);
        RecyclerView recyclerView = findViewById(R.id.rv_menu);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void openStopWatchActivity() {
        Intent intent = new Intent(getApplicationContext(), StopWatchActivity.class);
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