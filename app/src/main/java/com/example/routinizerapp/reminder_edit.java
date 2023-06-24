package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reminder_edit extends AppCompatActivity {
    Button save;
    Button delete;
    EditText layouttitle, layoutdesc,countertext1,timertext;
    RadioGroup radioGroup;
    String priority;
    TextView priorityh;
    String layoutmode;
    String layouttitleret,layoutdescret,countertext1ret,timertextret;
    String radioGroupret;
    FirebaseDatabase database=FirebaseDatabase.getInstance();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userId = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

         radioGroup = findViewById(R.id.radioGroup);
        layouttitle = findViewById(R.id.layouttitle);
        layoutdesc = findViewById(R.id.layoutdesc);
        timertext = findViewById(R.id.timertext);
        countertext1=findViewById(R.id.countertext1);
        priorityh=findViewById(R.id.priorityh);





        Intent intent = getIntent();
        if (intent != null) {
            layoutmode = intent.getStringExtra("click");
        }
        if(layoutmode.equals("todo")){
            timertext.setVisibility(View.GONE);
            countertext1.setVisibility(View.GONE);

        } else if (layoutmode.equals("counter")) {
            timertext.setVisibility(View.GONE);
        }
        else if (layoutmode.equals("reminder")) {
            countertext1.setVisibility(View.GONE);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    priority = "high";
                } else if (checkedId == R.id.radioButton2) {
                    priority = "medium";
                } else if (checkedId == R.id.radioButton3) {
                    priority = "low";
                }
            }
        });

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layouttitleret=layouttitle.getText().toString();
                layoutdescret=layoutdesc.getText().toString();
                timertextret=timertext.getText().toString();
                countertext1ret=countertext1.getText().toString();

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    // Find the RadioButton by its ID
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    // Get the string value of the selected RadioButton
                    radioGroupret = selectedRadioButton.getText().toString();
                }
                else {
                    Toast.makeText(reminder_edit.this, "select priority", Toast.LENGTH_SHORT).show();
                }

                ModelClass modelClass=new ModelClass(layoutmode,radioGroupret,layouttitleret,layoutdescret,countertext1ret,timertextret);
                database.getReference().child("routines").push().setValue(modelClass);



                Intent intent = new Intent(reminder_edit.this,MainPageFragment.class);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
