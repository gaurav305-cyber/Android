package com.example.agronepal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agronepal.R;
import com.example.agronepal.model.User1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactList extends Fragment {
    ListView lv1,lv2,lv3;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list1,list2,list3;
    ArrayAdapter<String> adapter1, adapter2, adapter3;
    User1 user1;




    public ContactList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact_list, container, false);
        lv1=view.findViewById(R.id.listView1);
        lv2=view.findViewById(R.id.listView2);
        lv3=view.findViewById(R.id.listView3);
        database=FirebaseDatabase.getInstance();

        user1=new User1();
        ref=database.getReference("Users");
        list1=new ArrayList<>();
        adapter1=new ArrayAdapter<String>(getActivity(), R.layout.user_info, R.id.userInfo, list1);
        list2=new ArrayList<>();
        adapter2=new ArrayAdapter<String>(getActivity(), R.layout.user_info, R.id.userInfo, list2);
        list3=new ArrayList<>();
        adapter3=new ArrayAdapter<String>(getActivity(), R.layout.user_info, R.id.userInfo, list3);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    user1=ds.getValue(User1.class);

                    if(!user1.getPhone().toString().isEmpty()) {
                        list1.add(user1.getUsername().toString());
                        list2.add((user1.getPhone().toString()));
                        list3.add((user1.getEmail().toString()));
                    }

                }
                lv1.setAdapter(adapter1);
                lv2.setAdapter(adapter2);
                lv3.setAdapter(adapter3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
