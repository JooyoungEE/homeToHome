package com.example.hometohome.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hometohome.R;
import com.example.hometohome.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MemoActivity extends AppCompatActivity {
    private Context context;
    static MemoAdapter memoAdapter;
    static ArrayList<Memo> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        this.context = getApplicationContext();

        memoAdapter = new MemoAdapter(context, notes);
        ListView listView = (ListView)findViewById(R.id.list_memo);
        listView.setAdapter(memoAdapter);

        Button btnAdd = (Button)findViewById(R.id.btn_add_note);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                startActivityForResult(intent, NoteEditorActivity.MEMO_REQUEST_CODE);
            }
        });
        //초기화면 세팅
        notes = selectAll();
        if (notes == null) {
            notes = new ArrayList<Memo>();
        }
        //클릭하면 편집화면으로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, NoteEditorActivity.MEMO_REQUEST_CODE);
            }
        });

        //길게 누르면 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemToDelete = position;

                new AlertDialog.Builder(MemoActivity.this)
                        .setTitle("확인")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(itemToDelete);
                                memoAdapter.setItems(notes);
                                memoAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("아니요", null).show();
                return true;
            }
        });

        //검색 기능 구현
        EditText editTextFilter = (EditText)findViewById(R.id.edit_text_filter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                if(search.length() > 0){
                    listView.setFilterText(search);
                }else{//검색어 지우면 필터 초기화
                    listView.clearTextFilter();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NoteEditorActivity.MEMO_REQUEST_CODE) {/*
            if (resultCode == RESULT_OK) {
                memoAdapter.setItems(notes);
                memoAdapter.notifyDataSetChanged();
            }*/
            memoAdapter.setItems(notes);
            memoAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 메모 리스트 가져오기
     */
    public ArrayList<Memo> selectAll() {
        String JSONString = CommonUtil.loadString(getApplicationContext(), Memo.MEMO_KEY);
        if (!TextUtils.isEmpty(JSONString)) {
            try {
                ArrayList<Memo> memos = new Gson().fromJson(JSONString, new TypeToken<List<Memo>>() {
                }.getType());
                return memos;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}