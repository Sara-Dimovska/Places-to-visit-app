package com.example.administrator.placestovisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import helpers.RetrofitClient;
import models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.PlaceService;
import services.RestorantsService;

public class NewPlaceActivity extends AppCompatActivity {

    EditText imageURLView, nameView, emailView, telephoneView, descriptionView, addressView;
    String imageURL, name, email, telephone, description, address, placeType;
    RadioGroup radioGroupView;
    RadioButton radioBtnChecked;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        submit = findViewById(R.id.submit);
        imageURLView = findViewById(R.id.enter_imgURL);
        nameView = findViewById(R.id.enter_name);
        emailView = findViewById(R.id.enter_email);
        telephoneView = findViewById(R.id.enter_telephone);
        descriptionView = findViewById(R.id.enter_desc);
        addressView = findViewById(R.id.enter_address);
        radioGroupView = findViewById(R.id.radios);
        // radioBtnChecked =


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

                PlaceService api = RetrofitClient.getClient().create(PlaceService.class);
                Call<Places> addNewPlace = api.post(place);

                addNewPlace.enqueue(new Callback<Places>() {
                    @Override
                    public void onResponse(Call<Places> call, Response<Places> response) {
                        if(!response.isSuccessful()) {
                            Log.d("RESPONSE_ERROR: ",String.valueOf(response.code()));
                            return;
                        }
                        Log.d("RESPONSE: ",String.valueOf(response.code()));
                        Places responsePlace = response.body();

                        Toast.makeText(NewPlaceActivity.this, "New place: " + responsePlace.getName() + " has been inserted",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Places> call, Throwable t) {
                        Toast.makeText(NewPlaceActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
