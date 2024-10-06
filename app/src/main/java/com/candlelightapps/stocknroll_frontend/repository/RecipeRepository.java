package com.candlelightapps.stocknroll_frontend.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.service.IngredientApiService;
import com.candlelightapps.stocknroll_frontend.service.RecipeApiService;
import com.candlelightapps.stocknroll_frontend.service.RetrofitInstance;

import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private final MutableLiveData<List<Recipe>> recipeListMutableLiveData;
    private final RecipeApiService recipeApiService;
    private Application application;

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
                recipeListMutableLiveData.setValue(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.i("HTTP Failure", Objects.requireNonNull(t.getMessage()));

            }
        });

        return recipeListMutableLiveData;
    }

    public void addRecipe(Recipe recipe) {
        Call<Recipe> call = recipeApiService.addRecipe(recipe);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Toast.makeText(application.getApplicationContext(),
                        String.format("Recipe %s added", recipe.getTitle()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                        "Invalid recipe. Unable to add to the database",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteRecipe(Long id) {
        Call<ResponseBody> call = recipeApiService.deleteRecipe(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(application.getApplicationContext(),
                        "Recipe removed from favourites",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to remove recipe from favourites",
                        Toast.LENGTH_SHORT).show();


            }
        });



    }
}
