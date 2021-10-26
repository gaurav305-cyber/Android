package com.example.agronepal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.agronepal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText e_mail1, password1;
    Button login;
    ProgressBar loading1;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth=FirebaseAuth.getInstance();
        e_mail1=(EditText) findViewById(R.id.email1);
        password1=(EditText) findViewById(R.id.pass1);
        loading1=findViewById(R.id.progressBar2);
        loading1.setVisibility(View.INVISIBLE);
        login = (Button) findViewById(R.id.btnsi);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading1.setVisibility(View.VISIBLE);
                login.setVisibility((View.INVISIBLE));
                String mail1= e_mail1.getText().toString();
                String pwd1=password1.getText().toString();
                if (mail1.isEmpty()){
                    e_mail1.setError("Please enter your email");
                    e_mail1.requestFocus();
                }
                else if (pwd1.isEmpty()){
                    password1.setError("please enter password");
                    password1.requestFocus();
                }
                else {
                    mFirebaseAuth.signInWithEmailAndPassword(mail1, pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login successfull!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), IndexActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Sorry!!! Some undifined error occured. Please login again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                    }

        });
    }
}
