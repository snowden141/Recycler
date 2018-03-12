package com.example.shobhraj.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shobhraj on 12/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<listItem> listItems;
    private Context context;

    public MyAdapter(List<listItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listItem listItem1= listItems.get(position);
        holder.heading.setText(listItem1.getHead());
        holder.desc.setText(listItem1.getDesc());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void add(List<listItem> items){
        this.listItems.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView heading;
        public TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);

            heading=itemView.findViewById(R.id.textviewhead);
            desc=itemView.findViewById(R.id.textdescription);
        }
    }
}
