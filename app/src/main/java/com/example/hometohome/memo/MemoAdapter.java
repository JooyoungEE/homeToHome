package com.example.hometohome.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hometohome.R;

import java.io.FilterReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MemoAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Memo> notes;
    private ArrayList<Memo> filteredList = notes;
    Filter listFilter;
    private ViewHolder viewHolder;

    public MemoAdapter(Context context, ArrayList<Memo> notes) {
        this.context = context;
        this.notes = notes;
    }

    public void setItems(ArrayList<Memo> items) {
        notes = items;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        Memo memo = notes.get(position);
        viewHolder.tit.setText(memo.getMemo());
        viewHolder.date.setText(memo.getDate());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0){
                results.values = notes;
                results.count = notes.size();
            }else{
                ArrayList<Memo> itemList = new ArrayList<Memo>();

                for(Memo item : notes){
                    if(item.getMemo().toUpperCase().contains(constraint.toString().toUpperCase())){
                        itemList.add(item);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Memo>) results.values;
            //데이터 추가
            if(results.count > 0 ){
                notifyDataSetChanged();
            }else{
                //데이터 없을때
                notifyDataSetInvalidated();
            }
        }
    }

    public class ViewHolder{
        private final TextView tit, date;

        public ViewHolder(View convertView){
            tit = convertView.findViewById(R.id.list_tit);
            date = convertView.findViewById(R.id.date_row);
        }
    }
}
