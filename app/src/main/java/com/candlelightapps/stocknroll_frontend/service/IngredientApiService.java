package com.candlelightapps.stocknroll_frontend.service;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IngredientApiService {

    @GET("ingredients")
    Call<List<Ingredient>> getAllIngredients();

    @POST("ingredients")
    Call<Ingredient> addIngredient(@Body Ingredient ingredient);

}
