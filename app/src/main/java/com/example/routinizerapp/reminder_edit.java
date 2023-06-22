package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reminder_edit extends AppCompatActivity {
reminderClass reminderClass;
    Button save;
    Button delete;
    EditText title,desc;
    RadioGroup group;
    String priority;
    TextView remindnum;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userId = user.getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        title =  findViewById(R.id.title1);
        desc = findViewById(R.id.desc);
remindnum= findViewById(R.id.rnum);

        Intent intent = getIntent();
        if (intent != null) {
            String count = intent.getStringExtra("count");
            if (count != null) {
                remindnum.setText(count);
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1:
                        priority="high";

                        break;
                    case R.id.radioButton2:
                        priority="medium";

                        break;
                    case R.id.radioButton3:
                        priority="low";

                        break;
                }
            }
        });


        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reminderClass= new reminderClass(remindnum.getText().toString(),title.getText().toString(),desc.getText().toString(),priority);
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                DatabaseReference reminderref = database1.getReference().child("users").child(userId).child("reminder").child(remindnum.getText().toString());
reminderref.child("title").setValue(title.getText().toString());
                reminderref.child("desc").setValue(desc.getText().toString());
                reminderref.child("priority").setValue(priority);




            }
        });



    }
}