package com.example.administrator.placestovisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import helpers.RetrofitClient;
import models.Places;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;

public class EditPlaceActivity extends AppCompatActivity {
    EditText imageURLView, nameView, emailView, telephoneView, descriptionView, addressView;
    String imageURL, name, email, telephone, description, address, placeType;
    RadioGroup radioGroupView;
    RadioButton radioBtnChecked;
    Button submit;
    int place_ID;
    PlaceService api;
    String place_type_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_place);

        submit = findViewById(R.id.submit);
        imageURLView = findViewById(R.id.enter_imgURL);
        nameView = findViewById(R.id.enter_name);
        emailView = findViewById(R.id.enter_email);
        telephoneView = findViewById(R.id.enter_telephone);
        descriptionView = findViewById(R.id.enter_desc);
        addressView = findViewById(R.id.enter_address);
        radioGroupView = findViewById(R.id.radios);

        Intent intent = getIntent();
        place_ID = intent.getExtras().getInt("Edit_ID");
        place_type_extra = intent.getStringExtra("Edit_place_type");
        api = RetrofitClient.getClient().create(PlaceService.class);

        //API for populating fields
        Call<Places> getPlace = api.get_place(place_ID);
        getPlace.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                Places responsePlace = response.body();
                imageURLView.setText(responsePlace.getImage());
                nameView.setText(responsePlace.getName());
                descriptionView.setText(responsePlace.getDescription());
                addressView.setText(responsePlace.getAddress());
                telephoneView.setText(responsePlace.getTelephone());
                emailView.setText(responsePlace.getEmail());

                switch (place_type_extra) {
                    case "restaurant":
                        radioGroupView.check(R.id.radioRestaurant);
                        break;
                    case "bar":
                        radioGroupView.check(R.id.radioBar);
                        break;
                    case "nightclub":
                        radioGroupView.check(R.id.radioNightclub);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Places> call, Throwable throwable) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageURL = imageURLView.getText().toString().trim();
                name = nameView.getText().toString().trim();
                email = emailView.getText().toString().trim();
                telephone = telephoneView.getText().toString().trim();
                description = descriptionView.getText().toString().trim();
                address = addressView.getText().toString().trim();
                int radioID = radioGroupView.getCheckedRadioButtonId();
                radioBtnChecked = findViewById(radioID);
                placeType = radioBtnChecked.getText().toString();

                Places place = new Places(placeType,  name,  address,  email,  description,  telephone,  imageURL);


                Call<ResponseBody> editPlace = api.edit_place(place_ID,place);
                editPlace.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            Log.d("RESPONSE_ERROR: ",String.valueOf(response.code()));
                            return;
                        }
                        Log.d("RESPONSE: ",String.valueOf(response.code()));
                        if(response.code() == 200) {
                            Toast.makeText(EditPlaceActivity.this, "Place: " + name + " has been updated",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditPlaceActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
