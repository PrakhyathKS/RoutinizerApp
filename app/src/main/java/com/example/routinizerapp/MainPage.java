package com.example.routinizerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity {
    Button expandButton;
    CalendarView calendarView;

    FloatingActionButton fbMain,fb1,fb2,fb3;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://routinizerapp-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("Info");


    boolean isbool=true;
    private boolean areButtonsVisible;

    LinearLayout linearLayout;

    //Button expandButton = findViewById(R.id.expandButton);


    private float convertDpToPixels(float dp) {
        return dp * getResources().getDisplayMetrics().density;
    }

    private void hideButtons() {
        // Animate and hide the additional floating action buttons
        fb1.animate().translationY(0).alpha(0).setStartDelay(100).start();
        fb2.animate().translationY(0).alpha(0).setStartDelay(50).start();
        fb3.animate().translationY(0).alpha(0).setStartDelay(0).start();
        fb1.setEnabled(false);
        fb2.setEnabled(false);
        fb3.setEnabled(false);
        areButtonsVisible = false;
    }
    private void showButtons() {
        // Set the initial translation values for the additional floating action buttons
        fb1.setTranslationY(0);
        fb2.setTranslationY(0);
        fb3.setTranslationY(0);

        // Calculate the translation values for the pop-out effect with reduced spacing
        float translation1 = -convertDpToPixels(10);
        float translation2 = -convertDpToPixels(20);
        float translation3 = -convertDpToPixels(30);

        // Enable the buttons
        fb1.setEnabled(true);
        fb2.setEnabled(true);
        fb3.setEnabled(true);

        // Animate and show the additional floating action buttons
        fb1.animate().translationY(translation1).alpha(1).setStartDelay(0).start();
        fb2.animate().translationY(translation2).alpha(1).setStartDelay(50).start();
        fb3.animate().translationY(translation3).alpha(1).setStartDelay(100).start();
        areButtonsVisible = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        expandButton=findViewById(R.id.expandButton);
       calendarView = findViewById(R.id.calendarView);
        calendarView.setVisibility(View.INVISIBLE);
        linearLayout = findViewById(R.id.hello);
        fbMain = findViewById(R.id.fbMain);
        fb1 = findViewById(R.id.fb1);
        fb2 = findViewById(R.id.fb2);
        fb3 = findViewById(R.id.fb3);

        Log.e("wwww","ssss");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  ArrayList<cardVal> StoreArr = new ArrayList<cardVal>();
                int i = 0;
                boolean flamingo = false;
                boolean apple = false;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                   // DataSnapshot testChildSnapshot = dataSnapshot1.child("test");
                    if (dataSnapshot1.exists() && dataSnapshot1.getValue() != null) {
                        String testValue = dataSnapshot1.getValue().toString();
                       // if (dataSnapshot1.child("test").getValue().toString().toLowerCase().contains("") ){
//                            String code = dataSnapshot1.child("Customer Code").getValue().toString();
//                            String Cname = dataSnapshot1.child("Customer Name").getValue().toString();
//                            String Cadd = dataSnapshot1.child("Customer Address").getValue().toString();
//                            cardVal cardVal = new cardVal(code, Cname, Cadd);
//                            StoreArr.add(cardVal);
//                            apple = true;
//
//                            String loc = dataSnapshot1.child("LOCATION").getValue().toString();
//                            loc = loc.replace('-', '.');
//                            loc = "geo:" + loc + "?q=" + loc;
                            Log.d("SUCCESSFULL", dataSnapshot1.getValue().toString());
                        //}
                    }

                    else {

                        Log.d("Yooooooooo","Roger");
                    }
                }

//                if (apple) {
//                    Intent listPage = new Intent(searchpage.this, ListPage.class);
//                    listPage.putExtra("StoreArr", StoreArr);
//                    startActivity(listPage);
//                    Log.d("55555555555", StoreArr.get(0).CustomerName);
//                    apple = false;
//                } else {
//                    Toast.makeText(searchpage.this, "Wrong Search data", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


        hideButtons();

        fbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areButtonsVisible) {
                    hideButtons();
                } else {
                    showButtons();
                }
            }
        });

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View box = LayoutInflater.from(MainPage.this).inflate(R.layout.reminder, linearLayout, false);
                linearLayout.addView(box);


                box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainPage.this,reminder_edit.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });

        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View box = LayoutInflater.from(MainPage.this).inflate(R.layout.todo, linearLayout, false);
                linearLayout.addView(box);
            }
        });

        fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View box = LayoutInflater.from(MainPage.this).inflate(R.layout.counter, linearLayout, false);
                linearLayout.addView(box);


            }
        });

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle the visibility of the CalendarView
                if (calendarView.getVisibility() == View.INVISIBLE) {

                    calendarView.setVisibility(View.VISIBLE);  // Hide the CalendarView
                } else {

                    calendarView.setVisibility(View.INVISIBLE);  // Show the CalendarView
                }
            }
        });
    }
}