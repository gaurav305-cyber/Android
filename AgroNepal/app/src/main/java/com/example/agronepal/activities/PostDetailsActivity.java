package com.example.agronepal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agronepal.R;
import com.example.agronepal.model.Experiences;

import java.util.List;

public class PostDetailsActivity extends AppCompatActivity {
    private EditText mTitle, mDesc;
    private Button mUpdate;
    private  String key;
    private  String title;
    private String experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        key=getIntent().getStringExtra("key");
        title=getIntent().getStringExtra("title");
        experience=getIntent().getStringExtra("experience");

        mTitle=findViewById(R.id.title);
        mTitle.setText(title);
        mDesc=findViewById(R.id.exp);
        mDesc.setText(experience);
        mUpdate=findViewById(R.id.buttonupdt);

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Experiences experiences = new Experiences();
                experiences.setTitle(mTitle.getText().toString());
                experiences.setExperience(mDesc.getText().toString());

                new DatabaseHelper().updatePost(key, experiences, new DatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Experiences> exps, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        startActivity(new Intent(getApplicationContext(), SharingActivity.class));
                        Toast.makeText(PostDetailsActivity.this, "Updated succesfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

    }

}
