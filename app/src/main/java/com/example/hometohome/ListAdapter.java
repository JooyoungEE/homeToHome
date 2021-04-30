package com.example.hometohome;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hometohome.R;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;
    private ViewHolder viewHolder;

    public ListAdapter(Context context, ArrayList<String> arrayList){
         this.context = context;
         this.arrayList = arrayList;
    }

    @Override
    public int getCount() { return arrayList.size(); }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.txt_name.setText(arrayList.get(position));
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.txt_name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    public class ViewHolder{
        private final TextView txt_name;
        private Button btn;

        public ViewHolder(View convertView){
            txt_name = convertView.findViewById(R.id.list_name);
            btn = convertView.findViewById(R.id.list_del);
        }
    }
}
