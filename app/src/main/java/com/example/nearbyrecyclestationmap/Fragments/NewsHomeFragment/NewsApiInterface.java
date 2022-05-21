package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//API interface to get articles from https://newsapi.org/docs/endpoints/everything
public interface NewsApiInterface {

    String BASE_URL="https://newsapi.org/v2/";

    @GET("everything")
    Call<NewsList> getNews(
            @Query("q") String query,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apikey
    );
}
