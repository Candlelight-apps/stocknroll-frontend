package com.candlelightapps.stocknroll_frontend.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.candlelightapps.stocknroll_frontend.repository.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {

    RecipeRepository recipeRepository;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        this.recipeRepository = new RecipeRepository(application);
    }


}
