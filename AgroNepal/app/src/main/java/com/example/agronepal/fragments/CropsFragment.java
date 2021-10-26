package com.example.agronepal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.agronepal.R;
import com.example.agronepal.activities.IndexActivity;
import com.example.agronepal.adapters.RecyclerViewAdapter;
import com.example.agronepal.model.Crops;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CropsFragment extends Fragment {
    private final String JSON_URL = "https://gist.githubusercontent.com/gauravRaut11/1e9c19c2a7bb37d5278f8c2bea8585ed/raw/0bd771bce44d205a105fdb6a0b9a0858ea185dd3/crops.json";
    private List<Crops> lstCrops;
    private RecyclerView recyclerView;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;

    public CropsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crops, container, false);
        lstCrops = new ArrayList<>() ;
        recyclerView = view.findViewById(R.id.recyclerviewid);
        jsonrequest();

        return view;
    }
    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Crops crop = new Crops() ;
                        crop.setName(jsonObject.getString("name"));
                        crop.setDescription(jsonObject.getString("description"));
                        crop.setCategorie(jsonObject.getString("categorie"));
                        crop.setImage_url(jsonObject.getString("img"));
                        lstCrops.add(crop);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstCrops);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Crops> lstCrops) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getActivity(),lstCrops) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
