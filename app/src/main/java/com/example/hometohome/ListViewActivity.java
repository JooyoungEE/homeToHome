package com.example.hometohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private Context context;
    private ArrayList<String> arrayList;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        this.context = getApplicationContext();

        arrayList = new ArrayList<>();
        arrayList.add("tae");
        arrayList.add("fany");
        arrayList.add("hyo");
        arrayList.add("sunny");
        arrayList.add("yuri");
        arrayList.add("sooyoung");
        arrayList.add("yoona");
        arrayList.add("seo");

        listAdapter = new ListAdapter(context, arrayList);
        listView = (ListView)findViewById(R.id.list_item);
        listView.setAdapter(listAdapter);
    }
}