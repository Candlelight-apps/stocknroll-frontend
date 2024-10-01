package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.service.ApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

public class IngredientRepository {

    private final MutableLiveData<Ingredient> ingredientsList;
    private final ApiService apiService;

    public IngredientRepository(Application application) {
        ingredientsList = new MutableLiveData<>();
        apiService = RetrofitInstance.
    }
}
