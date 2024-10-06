package com.candlelightapps.stocknroll_frontend.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.repository.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    RecipeRepository recipeRepository;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        this.recipeRepository = new RecipeRepository(application);
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getMutableLiveData();
    }

    public MutableLiveData<List<Recipe>> getRecipesByIngredients(List<String> ingredients) {
        return recipeRepository.getRecipesByIngredients(ingredients);
    }
    public MutableLiveData<List<Recipe>> getRecipesByCriteria(String diet,String cuisine,String intolerances) {
        return recipeRepository.getRecipesByCriteria(diet,cuisine,intolerances);
    }

}
