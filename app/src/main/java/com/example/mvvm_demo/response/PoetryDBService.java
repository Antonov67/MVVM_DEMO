package com.example.mvvm_demo.response;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PoetryDBService {

    @GET("random")
    Call<List<RandomPoem>> getRandomPoem();

}
