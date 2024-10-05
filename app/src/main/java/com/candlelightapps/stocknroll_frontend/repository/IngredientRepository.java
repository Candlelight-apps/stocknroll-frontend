package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.service.IngredientApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private MutableLiveData<List<Ingredient>> ingredientsList;
    private IngredientApiService ingredientApiService;
    Application application;


    public IngredientRepository(Application application) {
        ingredientsList = new MutableLiveData<>();
        this.application = application;
        ingredientApiService = RetrofitInstance.getRetrofitInstance().create(IngredientApiService.class);
    }

    public MutableLiveData<List<Ingredient>> getMutableLiveData() {
        Call<List<Ingredient>> call = ingredientApiService.getAllIngredients();

        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ingredientsList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Log.e("HTTP Failure", Objects.requireNonNull(t.getMessage()));
            }
        });
        return ingredientsList;
    }

    public void addIngredient(Ingredient ingredient) {
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

    public void updateIngredient(long id, int quantity) {

        Call<Ingredient> call = ingredientApiService.updateIngredient(id,quantity);
        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                Toast.makeText(application.getApplicationContext(),
                        String.format("Quantity updated for %s.", response.body().getName()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
                Log.e("update ingredient", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(application.getApplicationContext(),
                        "FAIL: Unable to update quantity",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteIngredient(long id) {
        IngredientApiService ingredientApiService = RetrofitInstance.getRetrofitInstance().create(IngredientApiService.class);
        Call<Ingredient> call = ingredientApiService.deleteIngredient(id);

        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                Toast.makeText(application.getApplicationContext(),
                        String.format("%s deleted!", response.body().getName()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                        "Invalid ingredient. Unable to delete from the database",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
