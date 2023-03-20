package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button button ,button1;
    EditText e1,e2,e3;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button2);
        button1 = findViewById(R.id.button3);
        e1 = findViewById(R.id.e);
        e2 = findViewById(R.id.editTextTextPersonName3);
        e3 = findViewById(R.id.editTextTextPersonName5);
        fauth = FirebaseAuth.getInstance();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String name = e1.getText().toString().trim();
                String email = e2.getText().toString().trim();
                String password = e3.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    e2.setError(("Email is Required"));
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    e3.setError("Password is Required");
                    return;
                }

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser fuser = fauth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this,"Register Successfull",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Registration Failure", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });



    }
}