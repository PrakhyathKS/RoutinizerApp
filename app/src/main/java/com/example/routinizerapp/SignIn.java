package com.example.routinizerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText etMailsign,etPassSignup,etRePass;
    Button btnSignup;
    FirebaseAuth mAuth;
    boolean check=false;

    public void createIntent() {
        Intent intent=new Intent(SignIn.this,LoginPage.class);
        startActivity(intent);
    }

    public void linkpressed(View view){
        Intent intent=new Intent(SignIn.this,LoginPage.class);
        startActivity(intent);
    }
    public void signin(View view){
        String email=etMailsign.getText().toString();
        String password=etPassSignup.getText().toString();
        String repass=etRePass.getText().toString();
        if (password.equals(repass) && !email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                Toast.makeText(SignIn.this, "Authentication sucess!!",
                                        Toast.LENGTH_SHORT).show();
                                createIntent();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etMailsign=findViewById(R.id.etMailsign);
        etPassSignup=findViewById(R.id.etPassSignup);
        etRePass=findViewById(R.id.etRePass);
        btnSignup=findViewById(R.id.btnSignup);
        mAuth=FirebaseAuth.getInstance();
    }
}