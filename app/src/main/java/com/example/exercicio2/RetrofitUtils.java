package com.example.exercicio2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://guisfco-online-shopping-api.herokuapp.com/api/online-shopping/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
