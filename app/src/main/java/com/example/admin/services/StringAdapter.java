package com.example.admin.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 8/14/2017.
 */

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    ArrayList<String> strings = new ArrayList<>();
    Context context;

    public StringAdapter(ArrayList<String> strings) {
        this.strings = strings;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tvString);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String string = strings.get(position);
        holder.text.setText(string);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


}
