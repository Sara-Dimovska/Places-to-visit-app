package services;

import java.util.List;
import models.Places;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestorantsService {
    @GET("get_restaurants")
    Call<List<Places>> get_restaurants();
}
