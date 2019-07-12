package com.example.recyclerviewpaging;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("popular")
    Call<StackApiRespnse> getPopularMovies(
            @Query("api_key") String api_key,
            @Query("page") int pageno
    );
}
