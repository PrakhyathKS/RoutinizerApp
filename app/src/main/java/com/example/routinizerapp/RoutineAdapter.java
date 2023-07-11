package com.example.routinizerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class RoutineAdapter extends RecyclerView.Adapter {

    public ArrayList<ModelClass> routineModel;
    Context context;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    int TODO_VIEW_TYPE = 2;
    int COUNTER_VIEW_TYPE = 3;
    int REMAINDER_VIEW_TYPE = 1;

    public RoutineAdapter(ArrayList<ModelClass> messagesModels, Context context) {
        this.routineModel = messagesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TODO_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.todo, parent, false);
            return new TodoViewHolder(view);
        }
        else if(viewType == COUNTER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.counter, parent, false);
            return new CounterViewHolder(view);
        }
        else if(viewType == REMAINDER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.reminder, parent, false);
            return new RemainderViewHolder(view);
        }
        else {
            Toast.makeText(context, "View Population Failed!!!", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    //test work


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ModelClass rtModel = routineModel.get(position);
//        if(rtModel.getLayoutmode().equals("reminder")) {
//
//        }else if(rtModel.getLayoutmode().equals("todo")) {
//
//        }else if(rtModel.getLayoutmode().equals("counter")) {
//
//        }

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("are sure you waant to delete it?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.getReference().child("routines").child(rtModel.getRoutineKey()).setValue(null);
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
//return false;
                .show();
            }

        }); */

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform the delete operation here
                        database.getReference().child("routines").child(rtModel.getRoutineKey()).setValue(null);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

        // Return true to indicate that the long click event is consumed
        return true;
    }
});



        if(holder.getClass() == RemainderViewHolder.class) {
            ((RemainderViewHolder) holder).remainderTime.setText(rtModel.getTimertext());
            ((RemainderViewHolder) holder).priorityRemainder.setText(rtModel.getRadioGroup());
            ((RemainderViewHolder) holder).remainderTitle.setText(rtModel.getLayouttitle());
        }
        else if(holder.getClass() == TodoViewHolder.class) {
            ((TodoViewHolder) holder).todoPriority.setText(rtModel.getRadioGroup());
            ((TodoViewHolder) holder).todoTitle.setText(rtModel.getLayouttitle());
        }
        else if(holder.getClass() == CounterViewHolder.class) {
            ((CounterViewHolder) holder).counterInc.setText(rtModel.getCountertext1());
            ((CounterViewHolder) holder).counterTitle.setText(rtModel.getLayouttitle());
            ((CounterViewHolder) holder).counterPriority.setText(rtModel.getRadioGroup());
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(routineModel.get(position).getLayoutmode().equals("reminder")) {
            return REMAINDER_VIEW_TYPE;
        }
        else if(routineModel.get(position).getLayoutmode().equals("todo")) {
            return TODO_VIEW_TYPE;
        }
        else if(routineModel.get(position).getLayoutmode().equals("counter")) {
            return COUNTER_VIEW_TYPE;
        }
        else {
            Toast.makeText(context, "Failed to determine the view type!!", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return routineModel.size();
    }

    public class RemainderViewHolder extends RecyclerView.ViewHolder {

        TextView remainderTitle, priorityRemainder, remainderTime;

        public RemainderViewHolder(@NonNull View itemView) {
            super(itemView);
            remainderTitle = itemView.findViewById(R.id.ReminderTitle);
            priorityRemainder = itemView.findViewById(R.id.priorityReminder);
            remainderTime = itemView.findViewById(R.id.remindertime);
        }
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView todoPriority, todoTitle;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTitle = itemView.findViewById(R.id.todotitle);
            todoPriority = itemView.findViewById(R.id.prioritytodo);
        }
    }

    public class CounterViewHolder extends RecyclerView.ViewHolder {

        TextView counterTitle, counterPriority, counterInc;

        public CounterViewHolder(@NonNull View itemView) {
            super(itemView);
            counterTitle = itemView.findViewById(R.id.countertitle);
            counterPriority = itemView.findViewById(R.id.prioritycounte);
            counterInc = itemView.findViewById(R.id.counterinc);
        }
    }



}
