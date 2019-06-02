package services;

import java.util.List;
import models.Places;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestorantsService {
    @GET("get_restaurants")
    Call<List<Places>> get_restaurants();

    @GET("search_restaurants")
    Call<List<Places>> search_restaurants(@Query("name") String name);
}
