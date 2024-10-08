package com.candlelightapps.stocknroll_frontend.ui.favouriterecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFavouriteRecipesBinding;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityMainBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FindRecipeByIngredientActivity;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FoundRecipesRecyclerViewInterface;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.RecipeAdapter;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.InventoryAdapter;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivityClickHandler;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.RecipeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FavouriteRecipesActivity extends AppCompatActivity implements FoundRecipesRecyclerViewInterface, RecipeAdapter.OnFavouriteBtnClickedListener {

    private ActivityFavouriteRecipesBinding binding;
    private RecipeViewModel recipeViewModel;
    private List<Recipe> recipeList;
    private FavouriteRecipesClickHandler clickHandler;

    private AutoCompleteTextView sortingDropdownMenu;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite_recipes);
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        clickHandler = new FavouriteRecipesClickHandler(this);
        binding.setClickHandler(clickHandler);
        getFavouriteRecipes();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            Intent intent;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.pantry) {
                    intent = new Intent(FavouriteRecipesActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;

                } else if (id == R.id.recipes) {
                    intent = new Intent(FavouriteRecipesActivity.this, FindRecipeByIngredientActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.favourites) {
                    intent = new Intent(FavouriteRecipesActivity.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    private void getFavouriteRecipes() {
        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipesFromLiveData) {
                recipeList = recipesFromLiveData;

                Collections.sort(recipeList, new Comparator<Recipe>() {
                    @Override
                    public int compare(Recipe o1, Recipe o2) {
                        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                    }
                });

                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.rvFavouriteRecipes;
        recipeAdapter = new RecipeAdapter(recipeList, this, this, this, recipeList);
        recyclerView.setAdapter(recipeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recipeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onFavouriteBtnClicked(Recipe recipe) {
        recipeViewModel.deleteRecipe(recipe.getId());
         Intent intent = new Intent(FavouriteRecipesActivity.this, FavouriteRecipesActivity.class);
        startActivity(intent);



    }
}