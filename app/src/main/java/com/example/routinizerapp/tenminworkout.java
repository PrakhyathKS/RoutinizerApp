package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tenminworkout extends AppCompatActivity {
    TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenminworkout);


        linkTextView = findViewById(R.id.linkTextView);
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open YouTube when the link is clicked
                String youtubeUrl = "https://youtu.be/7KSNmziMqog";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));

                // Check if YouTube app is installed, if not, open in a web browser
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // YouTube app is not installed, open in a web browser
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://youtu.be/7KSNmziMqog"));
                    startActivity(intent);
                }
            }
        });
    }

}