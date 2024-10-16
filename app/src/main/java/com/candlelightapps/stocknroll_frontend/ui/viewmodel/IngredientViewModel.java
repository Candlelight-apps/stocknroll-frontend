package com.candlelightapps.stocknroll_frontend.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.repository.IngredientRepository;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    IngredientRepository ingredientRepository;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
        this.ingredientRepository = new IngredientRepository(application);
    }

    private MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDeleted() {
        return isDeleted;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.addIngredient(ingredient);
    }

    public MutableLiveData<List<Ingredient>> getIngredients() {
        return ingredientRepository.getMutableLiveData();
    }

    public void deleteIngredient(long id) {
        ingredientRepository.deleteIngredient(id);
        isDeleted.setValue(true);
    }

    public void updateIngredient(long id, int quantity) {
        ingredientRepository.updateIngredient(id, quantity);
    }
}
