package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFindRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class FindRecipeByIngredientActivity extends AppCompatActivity {

    private List<Ingredient> ingredientList;
    private ArrayList<Ingredient> ingredientFilterList;

    private IngredientAdapter ingredientAdapter;
    private SearchView ingredientSearchView;
    private RecyclerView recyclerView;
    private ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding;
    private FindRecipeByIngredientActivityViewModel findRecipeByIngredientActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_recipe_by_ingredient);

        activityFindRecipeByIngredientBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_recipe_by_ingredient);
        findRecipeByIngredientActivityViewModel = new ViewModelProvider(this).get(FindRecipeByIngredientActivityViewModel.class);

        getAllIngredients();

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

    private void getAllIngredients() {
        findRecipeByIngredientActivityViewModel.getAllIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredientsLiveData) {
                ingredientList = (ArrayList<Ingredient>) ingredientsLiveData;
                displayInRecyclerView();
            }
        });

    }

    public void displayInRecyclerView() {
        recyclerView = activityFindRecipeByIngredientBinding.ingredientRecyclerView;
        ingredientAdapter = new IngredientAdapter(ingredientList, this);
        recyclerView.setAdapter(ingredientAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ingredientAdapter.notifyDataSetChanged();
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