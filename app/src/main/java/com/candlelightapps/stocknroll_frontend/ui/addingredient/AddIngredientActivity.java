package com.candlelightapps.stocknroll_frontend.ui.addingredient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityAddIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

public class AddIngredientActivity extends AppCompatActivity {

    private ActivityAddIngredientBinding binding;
    private AutoCompleteTextView categoryDropdownMenu;
    private AddIngredientClickHandler ingredientClickHandler;
    private Ingredient ingredient;
    IngredientViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_ingredient);

        initaliseCategoryDropdownMenu();

        ingredient = new Ingredient();

        viewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        ingredientClickHandler = new AddIngredientClickHandler(ingredient, this, viewModel);
        binding.setClickHandler(ingredientClickHandler);

        binding.setIngredient(ingredient);
    }

    private void initaliseCategoryDropdownMenu() {
        categoryDropdownMenu = binding.categoryDropdown;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.food_categories,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryDropdownMenu.setAdapter(adapter);
    }
}