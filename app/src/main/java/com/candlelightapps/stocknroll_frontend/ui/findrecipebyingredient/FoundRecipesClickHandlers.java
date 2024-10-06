package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class FoundRecipesClickHandlers {

    private Context context;
    private RecipeAdapter recipeAdapter;

    public FoundRecipesClickHandlers(Context context, RecipeAdapter recipeAdapter) {
        this.context = context;
        this.recipeAdapter = recipeAdapter;
    }

    public void backBtnClicked (View view) {
        Intent intent = new Intent(view.getContext(), FindRecipeByIngredientActivity.class);
        view.getContext().startActivity(intent);
    }
}
