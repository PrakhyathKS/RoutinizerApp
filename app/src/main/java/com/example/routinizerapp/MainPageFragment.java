package com.example.routinizerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {
    Button expandButton;
    CalendarView calendarView;

    FloatingActionButton fbMain, fb1, fb2, fb3;
    RecyclerView recyclerView;

   // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
   // String userId = user.getUid();

    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
   // DatabaseReference userNodeRef = database1.getReference().child("users").child(userId);

    boolean isbool = true;
    private boolean areButtonsVisible;

    LinearLayout linearLayout;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page, container, false);
       // expandButton = view.findViewById(R.id.expandButton);
       // calendarView = view.findViewById(R.id.calendarView);
        //calendarView.setVisibility(View.INVISIBLE);
       // linearLayout = view.findViewById(R.id.hello);
        fbMain = view.findViewById(R.id.fbMain);
        fb1 = view.findViewById(R.id.fb1);
        fb2 = view.findViewById(R.id.fb2);
        fb3 = view.findViewById(R.id.fb3);
        recyclerView = view.findViewById(R.id.recyclerView);


        final ArrayList<ModelClass> routineModels = new ArrayList<>();
        final RoutineAdapter adapter = new RoutineAdapter(routineModels, requireContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        database1.getReference().child("routines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                routineModels.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ModelClass model = snapshot1.getValue(ModelClass.class);
                    routineModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                Toast.makeText(requireContext(), "reminder", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(requireContext(), reminder_edit.class);
                intent.putExtra("click","reminder");
                startActivity(intent);
            }
        });

        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "todo", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(requireContext(), reminder_edit.class);
                intent.putExtra("click","todo");
                startActivity(intent);
            }
        });

        fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "counter", Toast.LENGTH_SHORT).show();
              Intent intent= new Intent(requireContext(), reminder_edit.class);
              intent.putExtra("click","counter");
                startActivity(intent);
            }
        });

//        expandButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toggle the visibility of the CalendarView
//                if (calendarView.getVisibility() == View.INVISIBLE) {
//                    calendarView.setVisibility(View.VISIBLE); // Show the CalendarView
//                } else {
//                    calendarView.setVisibility(View.INVISIBLE); // Hide the CalendarView
//                }
//            }
//        });



        return view;
    }
}
