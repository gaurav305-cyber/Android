package com.example.agronepal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agronepal.R;
import com.example.agronepal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText mail, password, cpassword, user, cNo;
    TextView signin;
    Button signup;
    RadioGroup rg;
    RadioButton rf, rs;
    String type="";
    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg= (RadioGroup) findViewById(R.id.rgbtn);
        rf=(RadioButton) findViewById(R.id.fbtn);
        rs=(RadioButton) findViewById(R.id.sbtn);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==R.id.sbtn){
                        cNo.setVisibility(View.VISIBLE);

                    }
                    else if(checkedId==R.id.fbtn){
                        cNo.setVisibility(View.GONE);
                    }
                }
            });
        cNo=findViewById(R.id.contact);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        cpassword=findViewById(R.id.cpass);
        user=findViewById(R.id.uname);
        loading=findViewById(R.id.regProgressBar);
        loading.setVisibility(View.INVISIBLE);
        mFirebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        signup=(Button) findViewById(R.id.subtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setVisibility(View.INVISIBLE);
                signin.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                String p = password.getText().toString();
                String cp = cpassword.getText().toString();
                final String email = mail.getText().toString();
                final String username=user.getText().toString();
                final String phone=cNo.getText().toString();
                if(rf.isChecked()){
                    type="Farmer";

                }
                else if(rs.isChecked()){
                    type="Speacialist";
                }

                if (p.equals(cp)) {
                    if (username.isEmpty()) {
                        user.setError("Please enter your username");
                        user.requestFocus();
                        signup.setVisibility(View.VISIBLE);
                        signin.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.INVISIBLE);
                    }

                        if (!rs.isChecked() && !rf.isChecked()) {
                            rs.setError("Please select one option");
                            mail.requestFocus();
                            signup.setVisibility(View.VISIBLE);
                            signin.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.INVISIBLE);
                        }
                            if (email.isEmpty()) {
                                mail.setError("Please enter your email");
                                mail.requestFocus();
                                signup.setVisibility(View.VISIBLE);
                                signin.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.INVISIBLE);
                            } else if (p.isEmpty()) {
                                password.setError("please enter password");
                                password.requestFocus();
                                signup.setVisibility(View.VISIBLE);
                                signin.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.INVISIBLE);
                            } else if (!(email.isEmpty() && p.isEmpty())) {
                                mFirebaseAuth.createUserWithEmailAndPassword(email, p).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                           User information = new User(username, phone, type, email);
                                        databaseReference.child(mFirebaseAuth.getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                     public void onComplete(@NonNull Task<Void> task) {


                                                        Toast.makeText(MainActivity.this, " Register successful", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(MainActivity.this, IndexActivity.class));


                                                }
                                            });

                                        } else {
                                            signup.setVisibility(View.VISIBLE);
                                            signin.setVisibility(View.VISIBLE);
                                            loading.setVisibility(View.INVISIBLE);
                                            FirebaseAuthException e=(FirebaseAuthException)task.getException();
                                            Log.e("MainActivity","Registration Failed",e);
                                            Toast.makeText(MainActivity.this, "Sorry!!! Register Unsuccessful", Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                });
                            } else {
                                signup.setVisibility(View.VISIBLE);
                                signin.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, "Sorry!!! Some undifined error occured", Toast.LENGTH_SHORT).show();

                            }
                        }
                else if (!p.equals(cp)){
                    signup.setVisibility(View.VISIBLE);
                    signin.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "please confirm your password", Toast.LENGTH_SHORT).show();
                        }

            }
        });

                    signin=(TextView) findViewById(R.id.sibtn);
                    signin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        });
        }
        }


