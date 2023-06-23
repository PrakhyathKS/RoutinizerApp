package com.example.routinizerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> remiderlist;

    Adapter(List<ModelClass> remiderlist){
    this.remiderlist=remiderlist;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder,parent,false);
     return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    //String rname=remiderlist.get(position).getReminder();
    //String rcount=remiderlist.get(position).getRemicounter();

    //dholder.setDate(rname,rcount);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ReminderName,Remicount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ReminderName=ReminderName.findViewById(R.id.ReminderName);
            Remicount=Remicount.findViewById(R.id.Remicount);
        }

        public void setDate(String rname, String rcount) {
            ReminderName.setText(rname);
            Remicount.setText(rcount);
        }
    }
}
