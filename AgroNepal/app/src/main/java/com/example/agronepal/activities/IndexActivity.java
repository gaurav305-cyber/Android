package com.example.agronepal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

//import com.example.agronepal.fragments.CropsFragment;
import com.example.agronepal.R;
import com.example.agronepal.fragments.ContactList;
import com.example.agronepal.fragments.CropsFragment;
import com.example.agronepal.fragments.ShareFragment;
import com.example.agronepal.model.User1;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IndexActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    private final String JSON_URL = "https://gist.githubusercontent.com/gauravRaut11/1e9c19c2a7bb37d5278f8c2bea8585ed/raw/0bd771bce44d205a105fdb6a0b9a0858ea185dd3/crops.json";
//    private List<Crops> lstCrops;
//    private RecyclerView recyclerView;
//    private JsonArrayRequest request ;
//    private RequestQueue requestQueue ;
    private DrawerLayout mNavDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setContentView(R.layout.nav_drawer_layout);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mNavDrawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this, mNavDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mNavDrawer.addDrawerListener(toggle);
        toggle.syncState();
      navigationView.setNavigationItemSelectedListener(this);

//        updateHeader();


//        lstCrops = new ArrayList<>() ;
//        recyclerView = findViewById(R.id.recyclerviewid);
//        jsonrequest();
         if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CropsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_crops);

        }


    }

//    private void jsonrequest() {
//
//        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                JSONObject jsonObject  = null ;
//
//                for (int i = 0 ; i < response.length(); i++ ) {
//
//
//                    try {
//                        jsonObject = response.getJSONObject(i) ;
//                        Crops crop = new Crops() ;
//                        crop.setName(jsonObject.getString("name"));
//                        crop.setDescription(jsonObject.getString("description"));
//                        crop.setCategorie(jsonObject.getString("categorie"));
//                        crop.setImage_url(jsonObject.getString("img"));
//                        lstCrops.add(crop);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//                setuprecyclerview(lstCrops);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//        requestQueue = Volley.newRequestQueue(IndexActivity.this);
//        requestQueue.add(request) ;
//
//
//    }
//
//    private void setuprecyclerview(List<Crops> lstCrops) {
//
//
//        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstCrops) ;
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(myadapter);
//
//    }

    @Override
    public void onBackPressed() {
        if(mNavDrawer.isDrawerOpen(GravityCompat.START)){
            mNavDrawer.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_crops:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CropsFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactList()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent in= new Intent(IndexActivity.this, LoginActivity.class);
                startActivity(in);
                break;
        }
        mNavDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void updateHeader(){
//        NavigationView navigationView=findViewById(R.id.navigation_view);
//
//        final FirebaseUser fu=FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Users");
//        final FirebaseUser user2=FirebaseAuth.getInstance().getCurrentUser();
//        dr.addValueEventListener(new ValueEventListener() {
//            TextView username=findViewById(R.id.nav_username);
//            final TextView email=findViewById(R.id.nav_email);
//            User1 ur1=new User1(username);
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot ds: dataSnapshot.getChildren()) {
//                    if(ds.getKey().equals(fu.getUid())){
//                        username.setText(g);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
