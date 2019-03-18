package services;

import models.User;
import models.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationService {
    @POST("login")
    Call<UserResponse> login(@Body User user);

}
