package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class reminder_edit extends AppCompatActivity {
    private static final String CHANNEL_DESCRIPTION = "Channel for reminders";
    private static final CharSequence CHANNEL_NAME = "Reminder Channel";
    private static final String CHANNEL_ID = "reminder_channel";
    Button save;
    Button delete;
    EditText layouttitle, layoutdesc, countertext1, timertext;
    RadioGroup radioGroup;
    String priority;
    String layoutmode;
    String layouttitleret, layoutdescret, countertext1ret, timertextret;
    String radioGroupret;
    FirebaseDatabase database = FirebaseDatabase.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        radioGroup = findViewById(R.id.radioGroup);
        layouttitle = findViewById(R.id.layouttitle);
        layoutdesc = findViewById(R.id.layoutdesc);
        timertext = findViewById(R.id.timertext);
        countertext1 = findViewById(R.id.countertext1);

        Intent intent = getIntent();
        if (intent != null) {
            layoutmode = intent.getStringExtra("click");
        }
        if (layoutmode.equals("todo")) {
            timertext.setVisibility(View.GONE);
            countertext1.setVisibility(View.GONE);
        } else if (layoutmode.equals("counter")) {
            timertext.setVisibility(View.GONE);
        } else if (layoutmode.equals("reminder")) {
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
                layouttitleret = layouttitle.getText().toString();
                layoutdescret = layoutdesc.getText().toString();
                timertextret = timertext.getText().toString();
                countertext1ret = countertext1.getText().toString();

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    radioGroupret = selectedRadioButton.getText().toString();
                } else {
                    Toast.makeText(reminder_edit.this, "Select priority", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (timertext.getVisibility() == View.VISIBLE) {
                    if (timertextret.isEmpty()) {
                        // Handle the case when time is empty
                        Toast.makeText(reminder_edit.this, "Please enter a valid time", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }


                ModelClass modelClass = new ModelClass(layoutmode, radioGroupret, layouttitleret, layoutdescret, countertext1ret, timertextret);
                database.getReference().child("routines").push().setValue(modelClass);

                // Schedule the notification at the specified time
                scheduleNotification(layouttitleret, timertextret);

                Intent intent = new Intent(reminder_edit.this, MainPageFragment.class);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete logic here
            }
        });
    }

    private void scheduleNotification(String title, String time) {
        if (timertext.getVisibility() == View.VISIBLE) {

            if (time.isEmpty()) {
                // Handle the case when time is empty
                Toast.makeText(this, "Please enter a valid time", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] parts = time.split(":");
            if (parts.length != 2) {
                // Handle the case when time format is incorrect
                Toast.makeText(this, "Invalid time format. Please enter time in HH:mm format", Toast.LENGTH_SHORT).show();
                return;
            }

            int hour, minute;
            try {
                hour = Integer.parseInt(parts[0]);
                minute = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                // Handle the case when time cannot be parsed to integers
                Toast.makeText(this, "Invalid time format. Please enter time in HH:mm format", Toast.LENGTH_SHORT).show();
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            long scheduledTime = calendar.getTimeInMillis();
            long currentTime = System.currentTimeMillis();
            long timeDifference = scheduledTime - currentTime;

            if (timeDifference <= 0) {
                // Handle the case when the scheduled time has already passed
                Toast.makeText(this, "Invalid time. Please enter a future time", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent notificationIntent = new Intent(this, NotificationReceiver.class);
            notificationIntent.putExtra("title", title);
            notificationIntent.putExtra("time", time);
            //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }
        }
    }
}