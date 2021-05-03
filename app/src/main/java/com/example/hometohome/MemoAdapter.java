package com.example.hometohome;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static com.example.hometohome.MemoActivity.memoAdapter;

public class MemoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> notes;
    private ViewHolder viewHolder;

    public MemoAdapter(Context context, ArrayList<String> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_memo, parent, false);
            viewHolder = new MemoAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MemoAdapter.ViewHolder)convertView.getTag();
        }

        viewHolder.tit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteId", position);
                context.startActivity(intent);
            }
        });

//        viewHolder.tit.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
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
//                                SharedPreferences sharedPreferences1 = context.getApplicationContext().getSharedPreferences("com.example.hometohoem", Context.MODE_PRIVATE);
//                                HashSet<String> set = new HashSet(MemoActivity.notes);
//                                sharedPreferences1.edit().putStringSet("notes", set).apply();
//                            }
//                        }).setNegativeButton("아니요", null).show();
//                return true;
//            }
//        });

        viewHolder.date.setText(getDate());
        return convertView;
    }

    private String getDate() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String getTime = simpleDateFormat.format(mDate);

        return getTime;
    }
    public class ViewHolder{
        private final TextView tit, date;

        public ViewHolder(View convertView){
            tit = convertView.findViewById(R.id.list_memo);
            date = convertView.findViewById(R.id.date_row);
        }
    }
}
