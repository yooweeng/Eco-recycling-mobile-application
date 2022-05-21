package com.example.nearbyrecyclestationmap.WebServices;

import com.example.nearbyrecyclestationmap.Model.DirectionPlaceModel.DirectionResponseModel;
import com.example.nearbyrecyclestationmap.Model.GoogleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    //call get request of given url and get GoogleResponseModel in response
    @GET
    Call<GoogleResponseModel> getNearByPlaces(@Url String url);

    @GET
    Call<DirectionResponseModel> getDirection(@Url String url);
}
