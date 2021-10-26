package com.example.agronepal.activities;

import androidx.annotation.NonNull;

import com.example.agronepal.model.Experiences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
   private FirebaseDatabase mDatabase;
   private DatabaseReference mReferencePost;
   private List<Experiences> exps=new ArrayList<>();


    public interface DataStatus{
       void DataIsLoaded(List<Experiences> exps, List<String> keys);
       void DataIsInserted();
       void DataIsUpdated();
       void DataIsDeleted();
   }
    public DatabaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        mReferencePost=mDatabase.getReference("Post");
    }
    public void readPosts(final DataStatus dataStatus){
        mReferencePost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exps.clear();
                List<String>keys=new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Experiences experiences=keyNode.getValue(Experiences.class);
                    exps.add(experiences);
                }
                dataStatus.DataIsLoaded(exps,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addPosts(Experiences experiences, final DataStatus dataStatus){
       String key=mReferencePost.push().getKey();
       mReferencePost.child(key).setValue(experiences).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               dataStatus.DataIsInserted();
           }
       });

    }
    public void updatePost(String key, Experiences experiences, final DataStatus dataStatus){
       mReferencePost.child(key).setValue(experiences).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               dataStatus.DataIsUpdated();
           }
       });

    }
    public void deletePost(String key,final DataStatus dataStatus){
        mReferencePost.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });

    }
}
