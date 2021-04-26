package com.example.exercicio2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    @GET("product")
    Call<List<Product>> getAll();

    @POST("product")
    Call<Product> create(@Body Product product);

    @PUT("product/{id}")
    Call<Product> update(@Path("id") long id, @Body Product product);

    @DELETE("product/{id}")
    Call<Void> delete(@Path("id") long id);
}
