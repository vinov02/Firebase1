package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    Button button;
    EditText editText,editText1;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.button);
        fauth = FirebaseAuth.getInstance();
        editText =findViewById(R.id.editTextTextPersonName);
        editText1 = findViewById(R.id.editTextTextPassword2);
        fauth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String name = e1.getText().toString().trim();
                String email = editText.getText().toString().trim();
                String password = editText1.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    editText.setError(("Email is Required"));
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    editText1.setError("Password is Required");
                    return;
                }
                // startActivity(new Intent(getApplicationContext(),MainActivity2.class));

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser fuser = fauth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity2.this,"Login Successfull",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity2.this, "Login Failure", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });



    }
}