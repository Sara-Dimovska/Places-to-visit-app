package com.example.administrator.placestovisit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import helpers.RetrofitClient;
import models.Places;
import models.Rating;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;
import services.RatingService;

public class PlaceDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nameView, descriptionView, addressView, emailView, telephoneView, ratingTextView;
    RatingBar ratingBar;
    Button button_sendFeedback;
    Integer place_id;

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
        ratingTextView =findViewById(R.id.ratingText);
        ratingBar = findViewById(R.id.starBar);
        button_sendFeedback = findViewById(R.id.sendFeedback);


        Intent intent = getIntent();
        place_id = intent.getExtras().getInt("PlaceID");

        PlaceService getPlaceAPI = RetrofitClient.getClient().create(PlaceService.class);
        Call<Places> getPlace_byID = getPlaceAPI.get_place(place_id);

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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingTextView.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        ratingTextView.setText("Very bad");
                        button_sendFeedback.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ratingTextView.setText("Need some improvement");
                        button_sendFeedback.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ratingTextView.setText("Good");
                        button_sendFeedback.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        ratingTextView.setText("Great");
                        button_sendFeedback.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        ratingTextView.setText("I love it");
                        button_sendFeedback.setVisibility(View.VISIBLE);
                        break;
                    default:
                        ratingTextView.setText("");
                }
            }

        });

        button_sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = PlaceDetailsActivity.this.getPreferences(Context.MODE_PRIVATE);
                Integer userID = sharedPref.getInt("userID", 1);
                sendFeedbackAPI(place_id,userID,(int)ratingBar.getRating());
            }
        });

    }
    protected void sendFeedbackAPI(Integer place_id, Integer user_id, final Integer rating) {
        Rating add_rating = new Rating(place_id,user_id,rating);

        //making api call
        RatingService api = RetrofitClient.getClient().create(RatingService.class);
        final Call<Rating> sendFeedback = api.post(add_rating);

        sendFeedback.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                ratingTextView.setText("");
                ratingBar.setVisibility(View.INVISIBLE);
                button_sendFeedback.setVisibility(View.INVISIBLE);
                Toast.makeText(PlaceDetailsActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
            }
        });
    }
}
