package com.example.hometohome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class MemoActivity extends AppCompatActivity {
    private Context context;
    static MemoAdapter memoAdapter;
    static ArrayList<String> notes = new ArrayList<>();

//    public boolean onOptionItemSelected(@Nullable MenuItem item){
//        super.onOptionsItemSelected(item);
//
//        if(item.getItemId() == R.id.btn_add_note){
//            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return false;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        this.context = getApplicationContext();

        ListView listView = findViewById(R.id.list_memo);
        listView.setAdapter(memoAdapter);

//        TextView dateRow = (TextView) findViewById(R.id.date_row);
//        dateRow.setText(getDate());
//
        Button btn_add_note = (Button) findViewById(R.id.btn_add_note);
        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.hometohome", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Example Note");
        } else {
            notes = new ArrayList(set);
        }
        memoAdapter = new MemoAdapter(context, notes);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
//                intent.putExtra("noteId", position);
//                startActivity(intent);
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                final int itemToDelete = position;
//
//                new AlertDialog.Builder(MemoActivity.this)
//                        .setTitle("확인")
//                        .setMessage("정말 삭제하시겠습니까?")
//                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                notes.remove(itemToDelete);
//                                memoAdapter.notifyDataSetChanged();
//                                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.hometohoem", Context.MODE_PRIVATE);
//                                HashSet<String> set = new HashSet(MemoActivity.notes);
//                                sharedPreferences.edit().putStringSet("notes", set).apply();
//                            }
//                        }).setNegativeButton("아니요", null).show();
//                return true;
//            }
//        });
//    }
//
//    private String getDate() {
//        long now = System.currentTimeMillis();
//        Date mDate = new Date(now);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
//        String getTime = simpleDateFormat.format(mDate);
//
//        return getTime;
//    }
    }
}