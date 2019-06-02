package services;

import java.util.List;

import models.Places;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BarsService {
    @GET("get_bars")
    Call<List<Places>> get_bars();

    @GET("search_bars")
    Call<List<Places>> search_bars(@Query("name") String name);
}
