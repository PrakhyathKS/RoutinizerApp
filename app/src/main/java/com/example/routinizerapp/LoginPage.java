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

public class LoginPage extends AppCompatActivity {
    EditText etMail,etPass;
    Button btnLogin;
    FirebaseAuth mAuth;

    public void linkpressedsignup(View view){
        Intent intent=new Intent(LoginPage.this,SignIn.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = etMail.getText().toString();
        String password = etPass.getText().toString();
        if(email.isEmpty() | password.isEmpty())
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginPage.this,MainPageWithNavBar.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginPage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        etMail=findViewById(R.id.etMail);
        etPass=findViewById(R.id.etPass);
        btnLogin=findViewById(R.id.btnLogin);
        mAuth=FirebaseAuth.getInstance();
    }
}