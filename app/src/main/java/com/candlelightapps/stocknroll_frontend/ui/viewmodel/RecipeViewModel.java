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

    private MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDeleted() {
        return isDeleted;
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getMutableLiveData();
    }

    public MutableLiveData<List<Recipe>> getRecipesByIngredients(List<String> ingredients) {
        return recipeRepository.getRecipesByIngredients(ingredients);
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.addRecipe(recipe);
    }

    public void deleteRecipe(long id) {
        recipeRepository.deleteRecipe(id);
        isDeleted.setValue(true);
    }

}
