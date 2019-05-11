package services;

import java.util.List;

import models.Places;
import models.Rating;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RatingService {
    @POST("add_rating")
    Call<Rating> post(@Body Rating rating);
}
