package com.example.administrator.placestovisit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import adapters.PlaceAdapter;
import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.RestorantsService;

public class RestorantsTab extends Fragment {

    List<Places> restorants;
    ListView listViewPlaces;
    Context context;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_restorants, container, false);
        context = getActivity().getApplicationContext();
        listViewPlaces = (ListView) rootView.findViewById(R.id.listViewRestorants);

        RestorantsService restorantsAPI = RetrofitClient.getClient().create(RestorantsService.class);
        Call<List<Places>> getRestorants = restorantsAPI.get_restaurants();

        getRestorants.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                restorants = (List<Places>)response.body();
                listViewPlaces.setAdapter(new PlaceAdapter(context, restorants));
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("RESTORANTS API FAILES", t.getMessage());
            }
        });


        return rootView;
    }
}
