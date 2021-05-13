package com.example.aconmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferenceActivity extends AppCompatActivity {
    private Context context;
    private TextView txt_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        context = this;

        String text = PreferenceManager.getString(context, "rebuild");
        if(text.equals("")){
            text = "저장된 데이터가 없습니다.";
            PreferenceManager.setString(context, "rebuild", "오늘은 월급날");
        }

        txt_preferences = (TextView)findViewById(R.id.txt_preferences);
        txt_preferences.setText(text);
    }
}