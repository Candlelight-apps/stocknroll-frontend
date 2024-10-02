package com.candlelightapps.stocknroll_frontend.service;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/ingredients/all")
    Call<List<Ingredient>> getAllIngredients();

    @GET("/api/recipes/all") // Replace with your actual API endpoint for recipes
    Call<List<Recipe>> getAllRecipes();
}
