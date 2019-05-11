package com.example.administrator.placestovisit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import adapters.PlaceAdapter;
import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;

public class SearchPlaceActivity extends AppCompatActivity {

    List<Places> results_list;
    EditText searchView;
    Button buttonView;
    ListView listViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        listViewResults = findViewById(R.id.search_listviewResults);
        searchView = findViewById(R.id.enter_search);
        buttonView = findViewById(R.id.button_search);

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchBy = searchView.getText().toString().trim();

                PlaceService searchAPI = RetrofitClient.getClient().create(PlaceService.class);
                Call<List<Places>> getSearchResult = searchAPI.search_places(searchBy);

                getSearchResult.enqueue(new Callback<List<Places>>() {
                    @Override
                    public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                        results_list = response.body();
                        listViewResults.setAdapter(new PlaceAdapter(getApplicationContext(), results_list));
                    }

                    @Override
                    public void onFailure(Call<List<Places>> call, Throwable t) {
                        Log.d("RESTORANTS API FAILES", t.getMessage());
                    }
                });
            }
        });


        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Places place_restaurant = (Places) listViewResults.getAdapter().getItem(i);

                Intent intent = new Intent(getApplicationContext(), PlaceDetailsActivity.class);
                intent.putExtra("PlaceID",place_restaurant.getId());
                startActivity(intent);
            }
        });


    }
}
