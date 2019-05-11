package com.example.administrator.placestovisit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import models.User;
import models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AuthenticationService;
import helpers.RetrofitClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView viewEmail;
    private EditText viewPassword;
    private Button submit;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewEmail = (AutoCompleteTextView) findViewById(R.id.email);
        viewPassword = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);



        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                email = viewEmail.getText().toString().trim();
                password = viewPassword.getText().toString().trim();

                User user = new User(email,password);

                //making api call
                AuthenticationService api = RetrofitClient.getClient().create(AuthenticationService.class);
                Call<UserResponse> login = api.login(user);

                login.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if(!response.isSuccessful()) {
                            Log.d("RESPONSE_ERROR: ",String.valueOf(response.code()));
                            return;
                        }
                        Log.d("RESPONSE: ",String.valueOf(response.code()));
                        UserResponse userResponse = response.body();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("userID", userResponse.getUser().getId());
                        editor.commit();

                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }


}

