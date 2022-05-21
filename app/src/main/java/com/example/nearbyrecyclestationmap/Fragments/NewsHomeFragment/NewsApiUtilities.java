package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//API from https://newsapi.org/docs/endpoints/everything
public class NewsApiUtilities {
    private static Retrofit retrofit = null;

    public static NewsApiInterface getNewsApiInterface() {
        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(NewsApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(NewsApiInterface.class);
    }
}
