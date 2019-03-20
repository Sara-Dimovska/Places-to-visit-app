package services;

import java.util.List;

import models.Places;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BarsService {
    @GET("get_bars")
    Call<List<Places>> get_bars();
}
