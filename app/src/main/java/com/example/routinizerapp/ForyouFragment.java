package com.example.routinizerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ForyouFragment extends Fragment {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foryou, container, false);

        cardView1 = view.findViewById(R.id.cardView1);
        cardView2 = view.findViewById(R.id.cardView2);
        cardView3 = view.findViewById(R.id.cardView3);
        cardView4 = view.findViewById(R.id.cardView4);

        cardView1.setOnClickListener(v -> {
            Intent intent=new Intent(requireContext(),fiveMinsRoutine.class);
            startActivity(intent);
        });
        cardView2.setOnClickListener(v -> {
            Intent intent=new Intent(requireContext(),tenminworkout.class);
            startActivity(intent);
        });
        cardView3.setOnClickListener(v -> {
            Intent intent=new Intent(requireContext(),morning_routine.class);
            startActivity(intent);
        });
        cardView4.setOnClickListener(v -> {
            Intent intent=new Intent(requireContext(),meditation.class);
            startActivity(intent);
        });

        return view;
    }


}
