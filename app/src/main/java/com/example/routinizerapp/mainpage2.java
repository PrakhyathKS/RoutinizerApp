package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class mainpage2 extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<ModelClass> remiderlist;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage2);

        initData();
        initRecycleView();
    }

    private void initData() {
        remiderlist =new ArrayList<>();
        remiderlist.add(new ModelClass("hello","1" ));
    }

    private void initRecycleView() {
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(mainpage2.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    adapter=new Adapter(remiderlist);
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();


    }
}