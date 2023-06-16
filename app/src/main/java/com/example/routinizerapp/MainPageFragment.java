package com.example.routinizerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainPageFragment extends Fragment {

    private Button expandButton;
    private CalendarView calendarView;
    private FloatingActionButton fbMain, fb1, fb2, fb3;
    private LinearLayout linearLayout;
    private boolean areButtonsVisible;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page, container, false);
        expandButton = view.findViewById(R.id.expandButton);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setVisibility(View.INVISIBLE);
        linearLayout = view.findViewById(R.id.hello);
        fbMain = view.findViewById(R.id.fbMain);
        fb1 = view.findViewById(R.id.fb1);
        fb2 = view.findViewById(R.id.fb2);
        fb3 = view.findViewById(R.id.fb3);

        hideButtons();

        fbMain.setOnClickListener(v -> {
            if (areButtonsVisible) {
                hideButtons();
            } else {
                showButtons();
            }
        });

        fb1.setOnClickListener(v -> {
            View box = LayoutInflater.from(requireContext()).inflate(R.layout.reminder, linearLayout, false);
            linearLayout.addView(box);
        });

        fb2.setOnClickListener(v -> {
            View box = LayoutInflater.from(requireContext()).inflate(R.layout.todo, linearLayout, false);
            linearLayout.addView(box);
        });

        fb3.setOnClickListener(v -> {
            View box = LayoutInflater.from(requireContext()).inflate(R.layout.counter, linearLayout, false);
            linearLayout.addView(box);
        });

        expandButton.setOnClickListener(v -> {
            // Toggle the visibility of the CalendarView
            if (calendarView.getVisibility() == View.INVISIBLE) {
                calendarView.setVisibility(View.VISIBLE);  // Show the CalendarView
            } else {
                calendarView.setVisibility(View.INVISIBLE);  // Hide the CalendarView
            }
        });

        return view;
    }
}

