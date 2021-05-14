package com.example.aconmemo.menu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aconmemo.*;
import com.example.aconmemo.memo.*;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private ArrayList<String> list;
    private Context context;

    public MenuAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_list_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder viewHolder, int position){
        viewHolder.menu.setText(list.get(position));

        viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = list.get(position);
                if(key.equals("StopWatch")){
                    Intent intent = new Intent(context, StopWatchActivity.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("HomeToHome")){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("ListView")){
                    Intent intent = new Intent(context, ListViewActivity.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("SharedPreference")){
                    Intent intent = new Intent(context, SharedPreferenceActivity.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("SharedPreference2")){
                    Intent intent = new Intent(context, SharedPreferenceActivity2.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("Memo")){
                    Intent intent = new Intent(context, MemoActivity.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }else if(key.equals("Memo_SQLite")){
                    Intent intent = new Intent(context, MemoActivity2.class);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}

class MenuViewHolder extends RecyclerView.ViewHolder{
    TextView menu;

    public MenuViewHolder(@NonNull View itemView){
        super(itemView);
        menu = itemView.findViewById(R.id.list_menu);
    }
}
