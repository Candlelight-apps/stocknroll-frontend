package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.repository.IngredientRepository;

import java.util.List;

public class FindRecipeByIngredientActivityViewModel extends AndroidViewModel {

    private IngredientRepository ingredientRepository;


    public FindRecipeByIngredientActivityViewModel(@NonNull Application application) {
        super(application);
        this.ingredientRepository = new IngredientRepository(application);
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return ingredientRepository.getMutableLiveData();
    }
}
