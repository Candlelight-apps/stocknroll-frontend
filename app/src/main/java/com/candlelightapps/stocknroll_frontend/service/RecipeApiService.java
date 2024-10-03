package com.candlelightapps.stocknroll_frontend.service;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApiService {

    @GET("recipes")
    Call<List<Recipe>> getAllRecipes();
}
