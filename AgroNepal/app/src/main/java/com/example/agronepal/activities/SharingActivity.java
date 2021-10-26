package com.example.agronepal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agronepal.R;
import com.example.agronepal.adapters.RecyclerView_Config;
import com.example.agronepal.model.Experiences;

import java.util.List;

public class SharingActivity extends AppCompatActivity {
private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);
        mRecyclerView=findViewById(R.id.rv);
        new DatabaseHelper().readPosts(new DatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Experiences> exps, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, SharingActivity.this, exps,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
