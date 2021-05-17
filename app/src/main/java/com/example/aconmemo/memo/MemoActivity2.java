package com.example.aconmemo.memo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aconmemo.R;

public class MemoActivity2 extends AppCompatActivity {
    private EditText listMemo;
    private long mMemold = -1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        listMemo = findViewById(R.id.editText);

        Intent intent = getIntent();
        if (intent != null) {
            mMemold = intent.getLongExtra("id", -1);
            String memo = intent.getStringExtra("memo");
            listMemo.setText(memo);
        }
    }

    //뒤로가기
    @Override
    public void onBackPressed() {
        String memo = listMemo.getText().toString();

        //SQLite에 저장
        ContentValues contentValues = new ContentValues();
        contentValues.put(MemoContract.MemoEntry.COLUMN_MEMO, memo);

        //DB에 전달
        SQLiteDatabase db = MemoDbHelper.getsInstance(this).getWritableDatabase();

        if (mMemold == -1) {
            long newRowID = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, contentValues);
            //저장 성공
            if (newRowID != -1) {
                setResult(RESULT_OK);
            }
        }else{
            //메모 내용 수정
            int count = db.update(MemoContract.MemoEntry.TABLE_NAME, contentValues, MemoContract.MemoEntry._ID+"="+mMemold, null);
            //수정 성공
            if(count != 0){
                setResult(RESULT_OK);
            }
        }
        super.onBackPressed();
    }
}
