package com.example.administrator.placestovisit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.RestorantsService;

public class RestorantsTab extends Fragment {

    List<Places> restorants;
    ListView listViewPlaces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_restorants, container, false);
        listViewPlaces = (ListView) rootView.findViewById(R.id.listViewRestorants);

        RestorantsService restorantsAPI = RetrofitClient.getClient().create(RestorantsService.class);
        Call<List<Places>> getRestorants = restorantsAPI.get_restorants();

        getRestorants.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                restorants = response.body();
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("RESTORANTS API FAILES", t.getMessage());
            }
        });


        return rootView;
    }
}
