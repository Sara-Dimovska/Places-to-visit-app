package services;

import models.Places;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlaceService {
    @POST("create_place")
    Call<Places> post(@Body Places place);

    @GET("/get_place/{id}")
    Call<Places> get_place(@Path("id") Integer id);

    @DELETE("/delete_place/{id}")
    Call<ResponseBody> delete_place(@Path("id") Integer id);
}
