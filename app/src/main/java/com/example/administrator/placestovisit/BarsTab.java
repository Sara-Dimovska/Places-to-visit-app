package com.example.administrator.placestovisit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import adapters.PlaceAdapter;
import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.BarsService;

public class BarsTab extends Fragment {

    List<Places> bars;
    ListView listViewPlaces;
    Context context;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_bars, container, false);
        context = getActivity().getApplicationContext();
        listViewPlaces = (ListView) rootView.findViewById(R.id.listViewBars);

        BarsService barsAPI = RetrofitClient.getClient().create(BarsService.class);
        Call<List<Places>> getBars = barsAPI.get_bars();

        getBars.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                bars = (List<Places>) response.body();
                listViewPlaces.setAdapter(new PlaceAdapter(context, bars));
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("BARS API FAILES", t.getMessage());
            }
        });


        return rootView;
    }
}
