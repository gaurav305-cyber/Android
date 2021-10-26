package com.example.agronepal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agronepal.R;
import com.example.agronepal.model.Experiences;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class View_Experience extends AppCompatActivity {

    private Button Updatebtn, Deletebtn;
    private  String key;
    private  String title;
    private String experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__experience);
        getSupportActionBar().hide();
        // Recieve data
        key=getIntent().getExtras().getString("key");
        title=getIntent().getExtras().getString("title");
        experience=getIntent().getExtras().getString("experience");
        Updatebtn=findViewById(R.id.buttonUpdate);
        Deletebtn=findViewById(R.id.buttondelete);


        // ini views

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
        final TextView tv_description = findViewById(R.id.aa_experience);
        final TextView tv_title = findViewById(R.id.aa_title);

        // setting values to each view

        tv_description.setText(experience);
        tv_title.setText(title);
        collapsingToolbarLayout.setTitle(title);
        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PostDetailsActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("title",tv_title.getText().toString());
                intent.putExtra("experience",tv_description.getText().toString());
                getApplicationContext().startActivity(intent);
            }

        });
        Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatabaseHelper().deletePost(key,new DatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Experiences> exps, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(View_Experience.this, "Deleted succesfully", Toast.LENGTH_SHORT).show();
                        finish();
                        return;

                    }
                });

            }
        });









    }

}
