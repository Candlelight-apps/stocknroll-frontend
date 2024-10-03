package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.service.IngredientApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private final MutableLiveData<List<Ingredient>> ingredientsList;
    private IngredientApiService ingredientApiService;
    Application application;


    public IngredientRepository(Application application) {
        ingredientsList = new MutableLiveData<>();
        this.application = application;
        ingredientApiService = RetrofitInstance.getRetrofitInstance().create(IngredientApiService.class);
    }

    public MutableLiveData<List<Ingredient>> getMutableLiveData() {
        ingredientApiService.getAllIngredients().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.isSuccessful() && response.body() != null) {
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

    public void addIngredient(Ingredient ingredient) {
        ingredientApiService = RetrofitInstance.getRetrofitInstance().create(IngredientApiService.class);
        Call<Ingredient> call = ingredientApiService.addIngredient(ingredient);

        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                Toast.makeText(application.getApplicationContext(),
                        String.format("Ingredient %s added", ingredient.getName()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Invalid ingredient. Unable to add to the database",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
