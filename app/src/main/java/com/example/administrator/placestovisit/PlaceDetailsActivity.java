package com.example.administrator.placestovisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;

public class PlaceDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nameView, descriptionView, addressView, emailView, telephoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        imageView = findViewById(R.id.imgIcon);
        nameView = findViewById(R.id.name);
        descriptionView = findViewById(R.id.description);
        addressView = findViewById(R.id.address);
        emailView = findViewById(R.id.email);
        telephoneView = findViewById(R.id.telephone);



        Intent intent = getIntent();
        Integer id = intent.getExtras().getInt("PlaceID");

        PlaceService getPlaceAPI = RetrofitClient.getClient().create(PlaceService.class);
        Call<Places> getPlace_byID = getPlaceAPI.get_place(id);

        getPlace_byID.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                Places place = response.body();

                Picasso.get().load(place.getImage()).into(imageView);
                nameView.setText(place.getName());
                descriptionView.setText(place.getDescription());
                addressView.setText(place.getAddress());
                emailView.setText(place.getEmail());
                telephoneView.setText(place.getTelephone());
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {

            }
        });

    }
}
