package com.example.aconmemo.memo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aconmemo.R;

import java.util.ArrayList;

public class MemoMainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        //작성 버튼
        Button btnAdd = (Button)findViewById(R.id.btn_add_note);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                startActivityForResult(intent, NoteEditorActivity.MEMO_REQUEST_CODE);
            }
        });
        ListView listView = findViewById(R.id.list_memo);

        Cursor cursor = getMemoCursor();
        mAdapter = new MemoAdapter(this, cursor);
        listView.setAdapter(mAdapter);

        //클릭하면 내용이 보여요
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MemoMainActivity.this, MemoActivity2.class);
                Cursor cursor = (Cursor)mAdapter.getItem(position);
                String memo = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_MEMO));
                intent.putExtra("memo", memo);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        //길게 누르면 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long deletedId = id;

                new AlertDialog.Builder(MemoMainActivity.this)
                        .setTitle("확인")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               //디비 불러오기
                                SQLiteDatabase db = MemoDbHelper.getsInstance(MemoMainActivity.this).getWritableDatabase();
                                int deletedCount = db.delete(MemoContract.MemoEntry.TABLE_NAME, MemoContract.MemoEntry._ID+ "=" + deletedId, null);

                                //삭제 성공
                                if(deletedCount != 0){
                                    mAdapter.swapCursor(getMemoCursor());
                                    Toast.makeText(MemoMainActivity.this, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("아니요", null).show();
                return true;
            }
        });
    }

    //메모 작성 후 초기화
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_INSERT && resultCode == RESULT_OK){
            mAdapter.swapCursor(getMemoCursor());
        }
    }

    //디비 조회 메소드
    private Cursor getMemoCursor() {
        MemoDbHelper dbHelper = MemoDbHelper.getsInstance(this);
        return dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, null, null, null, null, null);
    }

    //리스트뷰에 내용을 뿌리기 위한 어댑터
    private static class MemoAdapter extends CursorAdapter{
        public MemoAdapter(Context context, Cursor c){
            super(context, c);
        }

        //기본 뷰 뿌리기
        @Override
        public View newView(Context context, android.database.Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        //데이터 뿌리기
        @Override
        public void bindView(View view, Context context, android.database.Cursor cursor) {
            //
            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_MEMO)));
        }
    }
}
