package com.example.administrator.placestovisit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapters.PlaceAdapter;
import helpers.RetrofitClient;
import models.Places;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;
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
        listViewPlaces = rootView.findViewById(R.id.listViewRestorants);

        registerForContextMenu(listViewPlaces);

        populateRestaurants();

        listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Places place = (Places) listViewPlaces.getAdapter().getItem(i);

                 Intent intent = new Intent(context, PlaceDetailsActivity.class);
                 intent.putExtra("PlaceID",place.getId());
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void populateRestaurants() {
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
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item){
        PlaceService placeAPI = RetrofitClient.getClient().create(PlaceService.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Places place = (Places) listViewPlaces.getAdapter().getItem(index);


        if(item.getTitle()=="Edit")
            Toast.makeText(getContext(), "Edit Clicked", Toast.LENGTH_LONG).show();

        if(item.getTitle()=="Delete") {
            final String placeName = place.getName();
            Call<ResponseBody> deletePlace = placeAPI.delete_place(place.getId());
            deletePlace.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(getContext(), placeName + " have been deleted", Toast.LENGTH_LONG).show();
                    populateRestaurants();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                }
            });
        }

        return true;
    }
}
