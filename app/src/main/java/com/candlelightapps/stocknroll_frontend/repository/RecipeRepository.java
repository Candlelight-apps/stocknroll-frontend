package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;
import android.util.Log;

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

    private final MutableLiveData<List<Recipe>> recipeList;
    private final RecipeApiService recipeApiService;

    public RecipeRepository(Application application) {
        recipeList = new MutableLiveData<>();
        recipeApiService = RetrofitInstance.getRetrofitInstance().create(RecipeApiService.class);
    }

    public MutableLiveData<List<Recipe>> getMutableLiveData() {
        Call<List<Recipe>> call = recipeApiService.getAllRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    recipeList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                Log.e("HTTP Failure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return recipeList;
    }
}
