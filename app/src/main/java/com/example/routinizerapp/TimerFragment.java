package com.example.routinizerapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private EditText taskLabel;
    private TextView timerLabel;
    private Button startButton, stopButton, resetButton;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private long workDuration = 25 * 60 * 1000; // 25 minutes in milliseconds
    private long breakDuration = 5 * 60 * 1000; // 5 minutes in milliseconds

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        taskLabel = view.findViewById(R.id.taskLabel);
        timerLabel = view.findViewById(R.id.timerLabel);
        startButton = view.findViewById(R.id.startButton);
        stopButton = view.findViewById(R.id.stopButton);
        resetButton = view.findViewById(R.id.resetButton);

        taskLabel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String task = s.toString();
                // Save the task label or perform any necessary actions
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Pomodoro timer Started", Toast.LENGTH_SHORT).show();
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Pomodoro Timer Stopp" +
                        "ed", Toast.LENGTH_SHORT).show();
                stopTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Timer Rested", Toast.LENGTH_SHORT).show();
                resetTimer();
            }
        });

        updateTimerLabel(workDuration);
        isTimerRunning = false;

        return view;
    }

    private void startTimer() {
        if (!isTimerRunning) {
            countDownTimer = new CountDownTimer(workDuration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimerLabel(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    // Timer finished, start break timer
                    updateTimerLabel(0);
                    isTimerRunning = false;
                    startBreakTimer();
                }
            };

            countDownTimer.start();
            isTimerRunning = true;
        }
    }

    private void startBreakTimer() {
        if (!isTimerRunning) {
            countDownTimer = new CountDownTimer(breakDuration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimerLabel(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    // Break timer finished
                    updateTimerLabel(0);
                    isTimerRunning = false;
                }
            };

            countDownTimer.start();
            isTimerRunning = true;
        }
    }

    private void stopTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }

    private void resetTimer() {
        stopTimer();
        updateTimerLabel(workDuration);
    }

    private void updateTimerLabel(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;

        String timeLeft = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(timeLeft);
    }
}

