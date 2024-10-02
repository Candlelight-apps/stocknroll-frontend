package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.service.ApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private final MutableLiveData<List<Ingredient>> ingredientsList;
    private final ApiService apiService;

    public IngredientRepository(Application application) {
        ingredientsList = new MutableLiveData<>();
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    public MutableLiveData<List<Ingredient>> getMutableLiveData() {
        apiService.getAllIngredients().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    ingredientsList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                ingredientsList.setValue(null);

            }
        });
        return ingredientsList;
    }
}
