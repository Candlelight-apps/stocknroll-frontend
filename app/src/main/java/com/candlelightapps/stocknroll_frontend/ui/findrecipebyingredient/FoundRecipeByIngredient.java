package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.RecipeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FoundRecipeByIngredient extends AppCompatActivity implements FoundRecipesRecyclerViewInterface{

    private List<String> ingredientList;
    private ArrayList<String> criteria;
    private List<Recipe> recipeList;
    private ActivityFoundRecipeByIngredientBinding foundRecipeByIngredientBinding;
    private RecipeViewModel viewModel;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private TextView resultsCountText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_found_recipe_by_ingredient);

        foundRecipeByIngredientBinding = DataBindingUtil.setContentView(this, R.layout.activity_found_recipe_by_ingredient);

        viewModel = new ViewModelProvider(this)
                .get(RecipeViewModel.class);

        Bundle b = getIntent().getExtras();
        if(b.containsKey("ingredient_list")){
            ingredientList = b.getStringArrayList("ingredient_list");
            getRecipesByIngredients(ingredientList);
        }
        else {
          criteria = b.getStringArrayList("criteria");
          getRecipesByCriteria(criteria);
        }


        getRecipesByIngredients(ingredientList);

        resultsCountText = findViewById(R.id.recipesFoundCount);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        initaliseBottomNavigationMenu(bottomNavigationView);
   
    }
    private void getRecipesByCriteria(List<String> criteria) {
        viewModel.getRecipesByCriteria(criteria.get(0),criteria.get(1), criteria.get(2) ).observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeList = (ArrayList<Recipe>) recipes;
                displayInRecyclerView();
            }
        });
    }
    private void getRecipesByIngredients(List<String> ingredientList) {
        viewModel.getRecipesByIngredients(ingredientList).observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeList = (ArrayList<Recipe>) recipes;
                updateResultsCount(recipeList.size());
                displayInRecyclerView();
            }
        });
    }

    public void displayInRecyclerView() {
        recyclerView = foundRecipeByIngredientBinding.recipeRecyclerView;
        recipeAdapter = new RecipeAdapter(recipeList, this, this);
        recyclerView.setAdapter(recipeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recipeAdapter.notifyDataSetChanged();
    }

    private void updateResultsCount(int count) {
        String resultsText = count == 1 ? "1 result found" : count + " results found";
        resultsCountText.setText(resultsText);
    }

    @Override
    public void onItemClick(int position) {
        Recipe recipe = recipeList.get(position);

        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(recipe.getSourceUrl()));

        startActivity(intent);
    }

    private void initaliseBottomNavigationMenu(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            Intent intent;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.pantry) {
                    intent = new Intent(FoundRecipeByIngredient.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.recipes) {
                    intent = new Intent(FoundRecipeByIngredient.this, FindRecipeByIngredientActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.favourites) {
                    intent = new Intent(FoundRecipeByIngredient.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}