package services;

import java.util.List;

import models.Places;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NightclubsService {
    @GET("get_nightclubs")
    Call<List<Places>> get_nightclubs();
}
