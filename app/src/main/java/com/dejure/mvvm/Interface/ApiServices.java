package com.dejure.mvvm.Interface;

import com.dejure.mvvm.Model.ModelTvShow;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("most-popular")
    Call<ModelTvShow> getListOfTvShows(@Query("page") int page);

}
