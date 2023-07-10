//package com.example.routinizerapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class test extends AppCompatActivity {
//
//
//
//    FirebaseDatabase database = FirebaseDatabase.getInstance("https://routinizerapp-default-rtdb.firebaseio.com/");
//    DatabaseReference myRef = database.getReference("Info");
//    Button google;
//    LinearLayout linearLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        linearLayout = new LinearLayout(this );
//        linearLayout = findViewById(R.id.listpage);
//
//    google = findViewById(R.id.google);
//
//
//
//myRef.setValue("Yoooooooooooooooo");
//    }
//
//
//
//    public  void jai(View view) {
//
//        View box;
//        box = LayoutInflater.from(this).inflate(R.layout.list_item2, linearLayout, false);
//
//        linearLayout.addView(box);
//    }
//
//}