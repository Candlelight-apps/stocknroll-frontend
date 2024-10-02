package com.candlelightapps.stocknroll_frontend.ui.addingredient;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivityClickHandler;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

public class AddIngredientActivity extends AppCompatActivity {

//    private ActivityAddIngredientBinding binding;
    private AddIngredientClickHandler ingredientClickHandler;
    private Ingredient ingredient;
    IngredientViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        ingredient = new Ingredient();

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ingredient);

        viewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        ingredientClickHandler = new AddIngredientClickHandler(ingredient, this, viewModel);
//        binding.setIngredient(ingredient);
    }
}