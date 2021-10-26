package com.example.agronepal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agronepal.R;
import com.example.agronepal.activities.DatabaseHelper;
import com.example.agronepal.activities.IndexActivity;
import com.example.agronepal.activities.LoginActivity;
import com.example.agronepal.activities.MainActivity;
import com.example.agronepal.activities.SharingActivity;
import com.example.agronepal.model.Experiences;
import com.example.agronepal.model.User1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {
   private EditText header, post;
    Button postbtn, forwardbtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mFirebaseAuth;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_share, container, false);
        header=view.findViewById(R.id.title);
        post=view.findViewById(R.id.exp);
        postbtn=view.findViewById(R.id.btnpost);
        forwardbtn=view.findViewById(R.id.frdbtn);
        forwardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SharingActivity.class));
            }
        });
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Experiences experiences=new Experiences();
                experiences.setTitle(header.getText().toString());
                experiences.setExperience(post.getText().toString());
                startActivity(new Intent(getActivity(), SharingActivity.class));
                new DatabaseHelper().addPosts(experiences, new DatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Experiences> exps, List<String> keys) {
                        Toast.makeText(getActivity(), "Post added successfully!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), SharingActivity.class));
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
        });
        return view;
    }


}
