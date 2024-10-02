package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.repository.IngredientRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    IngredientRepository ingredientRepository;

    public MainActivityViewModel(@NonNull Application application){
        super(application);
        this.ingredientRepository = new IngredientRepository(application);
    }

    public MutableLiveData<List<Ingredient>> getIngredients() {
        return ingredientRepository.getMutableLiveData();
    }


}
