package com.example.aconmemo.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aconmemo.R;
import com.example.aconmemo.util.CommonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

public class NoteEditorActivity extends AppCompatActivity {

    public static int MEMO_REQUEST_CODE = 9999;
    private int position;
    private Memo memo;
    private boolean isReg = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        System.out.println("onCreate");

        EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        String json = "";

        position = intent.getIntExtra("position", -1);
        isReg = position == -1;
        if (isReg) { // 등록할 경우
            memo = new Memo();
        } else { // 수정할 경우
            memo = MemoActivity.notes.get(position);
            editText.setText(memo.getMemo());
        }
        Button btnSave = (Button)findViewById(R.id.btn_save_note);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장 버튼 누를 경우 결과값 리턴
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");

        EditText editText = findViewById(R.id.editText);
        String text = editText.getText().toString();
        if (!TextUtils.isEmpty(text.trim())) {
            memo.setMemo(text);
            memo.setDate(CommonUtil.getTimestamp("yyyy/MM/dd hh:mm"));
            if (isReg) {
                MemoActivity.notes.add(memo);
            }
            // 메모 리스트 저장하기
            NoteEditorActivity.insert(getApplicationContext(), MemoActivity.notes);
        }
    }

    /**
     * 메모 리스트 저장하기
     */
    public static void/*int*/ insert(Context context, ArrayList<Memo> memos) {
        if (memos != null && !memos.isEmpty()) {
            String JSONString = new Gson().toJson(memos);
            CommonUtil.saveString(context, Memo.MEMO_KEY, JSONString);
        }
    }
}