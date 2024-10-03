package com.candlelightapps.stocknroll_frontend.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.repository.IngredientRepository;

public class IngredientViewModel extends AndroidViewModel {

    IngredientRepository ingredientRepository;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.addIngredient(ingredient);
    }
}
