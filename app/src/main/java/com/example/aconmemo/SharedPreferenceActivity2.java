package com.example.aconmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferenceActivity2 extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference2);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    public void clickSetBtn(View view){
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(this, "값을 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("input Text", editText.getText().toString());
            editor.commit();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickGetBtn(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        String inputText = sharedPreferences.getString("input Text", "");
        textView.setText(inputText);
        Toast.makeText(this, "값을 불러왔습니다.", Toast.LENGTH_SHORT).show();
    }
}