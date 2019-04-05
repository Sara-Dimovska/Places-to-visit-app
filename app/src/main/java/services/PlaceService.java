package services;

import models.Places;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PlaceService {
    @POST("create_place")
    Call<Places> post(@Body Places place);
}
