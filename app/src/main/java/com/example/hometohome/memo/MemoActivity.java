package com.example.hometohome.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hometohome.R;
import com.example.hometohome.util.CommonUtil;
import com.example.hometohome.util.HangulUtill;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity {
    private Context context;
    static MemoAdapter memoAdapter;
    static ArrayList<Memo> notes = new ArrayList<>(); //원본데이터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        this.context = getApplicationContext();

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
        memoAdapter = new MemoAdapter(context, notes);
        ListView listView = (ListView)findViewById(R.id.list_memo);
        listView.setAdapter(memoAdapter);

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
                //메모 검색 방법1 - 초성 검색
                ArrayList<Memo> newnotes = new ArrayList<>(); //검색된 데이터
                String search = s.toString();
                if(search.length() > 0) {
                    for(int k = 0; k < notes.size(); k++){
                        Memo memo = notes.get(k);
                        String content = memo.getMemo();
                        Log.e("Memo List", "Memo List 문자열 : " + content);
                        boolean sResult = HangulUtill.searchString(content, search);
                        if (sResult) {
                            newnotes.add(memo);
                        }
                    }
                }
                memoAdapter.setItems(newnotes);
            }
            @Override
            public void afterTextChanged(Editable s) {
                /* 메모 검색 방법2
                String filterText = s.toString();
                if(filterText.length() > 0){
                    listView.setFilterText(filterText);
                }else{//검색어 지우면 필터 초기화
                    listView.clearTextFilter();
                }*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonUtil.commitString(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NoteEditorActivity.MEMO_REQUEST_CODE) {/*
            if (resultCode == RESULT_OK) {
                memoAdapter.setItems(notes);
            }*/
            memoAdapter.setItems(notes);
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