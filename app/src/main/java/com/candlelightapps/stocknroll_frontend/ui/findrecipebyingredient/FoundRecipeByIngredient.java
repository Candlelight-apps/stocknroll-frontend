package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoundRecipeByIngredient extends AppCompatActivity implements FoundRecipesRecyclerViewInterface, RecipeAdapter.OnFavouriteBtnClickedListener {

    private List<String> ingredientList;
    private List<Recipe> recipeList;
    private List<Recipe> favouriteRecipesList;
    private ActivityFoundRecipeByIngredientBinding foundRecipeByIngredientBinding;
    private RecipeViewModel recipeViewModel;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_found_recipe_by_ingredient);

        foundRecipeByIngredientBinding = DataBindingUtil.setContentView(this, R.layout.activity_found_recipe_by_ingredient);

        recipeViewModel = new ViewModelProvider(this)
                .get(RecipeViewModel.class);

        Bundle b = getIntent().getExtras();
        ingredientList = b.getStringArrayList("ingredient_list");

        getFavouriteRecipes();

        getRecipesByIngredients(ingredientList);
    }

    private void getRecipesByIngredients(List<String> ingredientList) {
        recipeViewModel.getRecipesByIngredients(ingredientList).observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeList = (ArrayList<Recipe>) recipes;
                displayInRecyclerView();
            }
        });
    }

    public void onFavouriteBtnClicked(Recipe recipe) {
        recipeViewModel.addRecipe(recipe);

    }

    public void displayInRecyclerView() {
        recyclerView = foundRecipeByIngredientBinding.recipeRecyclerView;
        recipeAdapter = new RecipeAdapter(recipeList, this, this, this, favouriteRecipesList);
        recyclerView.setAdapter(recipeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recipeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(int position) {
        Recipe recipe = recipeList.get(position);

        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(recipe.getSourceUrl()));

        startActivity(intent);

    }

    private void getFavouriteRecipes() {
        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipesFromLiveData) {
                favouriteRecipesList = recipesFromLiveData;
                Log.i("Favourite recipes", "**********Favourite recipes in activity ::" + favouriteRecipesList);
            }
        });
    }
}