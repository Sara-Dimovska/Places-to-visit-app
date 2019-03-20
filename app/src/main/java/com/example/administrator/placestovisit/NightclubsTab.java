package com.example.administrator.placestovisit;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import services.BarsService;
import services.NightclubsService;

public class NightclubsTab extends Fragment {
    List<Places> nightclubs;
    ListView listViewPlaces;
    Context context;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_bars, container, false);
        context = getActivity().getApplicationContext();
        listViewPlaces = (ListView) rootView.findViewById(R.id.listViewBars);

        NightclubsService nightclubsServiceAPI = RetrofitClient.getClient().create(NightclubsService.class);
        Call<List<Places>> getNightclubs = nightclubsServiceAPI.get_nightclubs();

        getNightclubs.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                nightclubs = (List<Places>) response.body();
                listViewPlaces.setAdapter(new PlaceAdapter(context, nightclubs));
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("NIGHTCLUBS API FAILES", t.getMessage());
            }
        });


        return rootView;
    }
}
