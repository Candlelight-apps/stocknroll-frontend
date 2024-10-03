package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.service.RecipeApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;

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
        recipeApiService.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    recipeList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                recipeList.setValue(null);

            }
        });
        return recipeList;
    }
}
