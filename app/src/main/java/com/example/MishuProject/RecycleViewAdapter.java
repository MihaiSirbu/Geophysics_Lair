package com.example.MishuProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    String myTitles[];
    ArrayList<String> myData;
    Context myContext;

    public RecycleViewAdapter(Context ct, String titles[], ArrayList<String> data){
        myContext = ct;
        myTitles = titles;
        myData = data;
    }
    @NonNull
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.my_row1,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.txt1.setText(myTitles[position]);
        holder.txt2.setText(myData.get(position));
    }

    @Override
    public int getItemCount() {
        return myTitles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt1,txt2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.TextViewTitleDisplay);
            txt2 = itemView.findViewById(R.id.TextViewDataDisplay);
        }
    }


}
