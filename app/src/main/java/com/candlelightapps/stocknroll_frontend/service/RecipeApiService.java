package com.candlelightapps.stocknroll_frontend.service;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApiService {

    @GET("recipes")
    Call<List<Recipe>> getAllRecipes();

    @POST("recipes")
    Call<Recipe> addRecipe(@Body Recipe recipe);

    @DELETE("recipes/{id}")
    Call<ResponseBody> deleteRecipe(@Path("id") long id);

    @GET("recipes/criteria")
    Call<List<Recipe>> getRecipesByCriteria(@Path("cuisine") String cuisine, @Path("diet") String diet, @Path("intolerances") String intolerances);

    @GET("recipes/ingredient")
    Call<List<Recipe>> getRecipesByIngredients(@Query("values") List<String> ingredients);
}
