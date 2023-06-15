package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class reminder_edit extends AppCompatActivity {

    Button save;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);



    }
}