package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.service.RecipeApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private final MutableLiveData<List<Recipe>> recipeListMutableLiveData;
    private final RecipeApiService recipeApiService;
    Application application;

    public RecipeRepository(Application application) {
        recipeListMutableLiveData = new MutableLiveData<>();
        recipeApiService = RetrofitInstance.getRetrofitInstance().create(RecipeApiService.class);
        this.application = application;
    }

    public MutableLiveData<List<Recipe>> getMutableLiveData() {
        Call<List<Recipe>> call = recipeApiService.getAllRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    recipeListMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                Log.e("HTTP Failure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return recipeListMutableLiveData;
    }

    public MutableLiveData<List<Recipe>> getRecipesByIngredients(List<String> ingredients) {
        Call<List<Recipe>> call = recipeApiService.getRecipesByIngredients(ingredients);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipeList = response.body();
                if(recipeList == null || recipeList.isEmpty()) Toast.makeText(application.getApplicationContext(), "Sorry!, No recipes found.", Toast.LENGTH_LONG).show();
                recipeListMutableLiveData.setValue(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Sorry!, No recipes found.", Toast.LENGTH_LONG).show();
                Log.i("HTTP Failure", Objects.requireNonNull(t.getMessage()));

            }
        });

        return recipeListMutableLiveData;
    }

    public MutableLiveData<List<Recipe>> getRecipesByCriteria(String cuisine, String diet, String intolerances) {
        Call<List<Recipe>> call = recipeApiService.getRecipesByCriteria(cuisine,diet,intolerances);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipeList = response.body();
                if(recipeList == null || recipeList.isEmpty()) Toast.makeText(application.getApplicationContext(), "Sorry!, No recipes found.", Toast.LENGTH_LONG).show();
                recipeListMutableLiveData.setValue(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Toast.makeText(application.getApplicationContext(), "Sorry!, No recipes found.", Toast.LENGTH_LONG).show();
                Log.i("HTTP Failure", Objects.requireNonNull(t.getMessage()));

            }
        });

        return recipeListMutableLiveData;
    }
}
