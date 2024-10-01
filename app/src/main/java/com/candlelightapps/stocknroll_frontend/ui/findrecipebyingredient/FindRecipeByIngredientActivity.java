package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class FindRecipeByIngredientActivity extends AppCompatActivity {

    private IngredientAdapter ingredientAdapter;
    private List<Ingredient> ingredientList;
    private ArrayList<Ingredient> ingredientFilterList;
    private SearchView ingredientSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_recipe_by_ingredient);

        ingredientSearchView = findViewById(R.id.searchItems);
        ingredientSearchView.clearFocus();

        ingredientSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterIngredientList(newText);
                return true;
            }
        });

    }

    private void filterIngredientList(String text) {

        ingredientFilterList = new ArrayList<>();

        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getCategory().toLowerCase().contains(text.toLowerCase()) ||
                    ingredient.getName().toLowerCase().contains(text.toLowerCase())) {
                ingredientFilterList.add(ingredient);
            }
        }

        if (ingredientFilterList.isEmpty()) {
            Toast.makeText(FindRecipeByIngredientActivity.this, "No Ingredients By Name or Category Found", Toast.LENGTH_SHORT).show();
        } else {
            ingredientAdapter.setIngredientFilteredList(ingredientFilterList);
        }
    }
}