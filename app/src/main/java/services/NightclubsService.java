package services;

import java.util.List;

import models.Places;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NightclubsService {
    @GET("get_nightclubs")
    Call<List<Places>> get_nightclubs();

    @GET("search_nightclubs")
    Call<List<Places>> search_nightclubs(@Query("name") String name);
}
