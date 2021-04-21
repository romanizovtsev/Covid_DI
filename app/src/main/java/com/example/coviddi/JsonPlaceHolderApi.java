package com.example.coviddi;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET ("history")
    Call<post1> getPost(@Query("country") String country,@Query("status") String status);
}
