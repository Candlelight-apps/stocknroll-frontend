package com.candlelightapps.stocknroll_frontend.ui.addingredient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityAddIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FindRecipeByIngredientActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AddIngredientActivity extends AppCompatActivity {

    private ActivityAddIngredientBinding binding;
    private AutoCompleteTextView categoryDropdownMenu;
    private AddIngredientClickHandler ingredientClickHandler;
    private Ingredient ingredient;
    IngredientViewModel viewModel;
    private TextView expiryDateText;
    private Button expiryDateButton;

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

        expiryDateText = findViewById(R.id.ingredientExpiryDate);
        expiryDateButton = findViewById(R.id.expiryDateButton);

        expiryDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        initaliseBottomNavigationMenu(bottomNavigationView);
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

    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);

    private void openDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                expiryDateText.setText(String.format("%s-%s-%s", year, month + 1, day));
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }

    private void initaliseBottomNavigationMenu(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            Intent intent;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.pantry) {
                    intent = new Intent(AddIngredientActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.recipes) {
                    intent = new Intent(AddIngredientActivity.this, FindRecipeByIngredientActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.favourites) {
                    intent = new Intent(AddIngredientActivity.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}