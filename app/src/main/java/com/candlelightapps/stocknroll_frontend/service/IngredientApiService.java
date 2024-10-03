package com.candlelightapps.stocknroll_frontend.service;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IngredientApiService {

    @GET("ingredients")
    Call<List<Ingredient>> getAllIngredients();

    @POST("ingredients")
    Call<Ingredient> addIngredient(@Body Ingredient ingredient);

    @DELETE("ingredients/{id}")
    Call<Ingredient> deleteIngredient(@Path("id") long id);

    @PATCH
    Call<Ingredient> updateIngredient(@Path("id") long id, @Body int quantity);
}
