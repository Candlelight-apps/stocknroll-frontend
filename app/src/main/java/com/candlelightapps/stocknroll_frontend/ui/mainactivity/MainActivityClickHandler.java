package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.candlelightapps.stocknroll_frontend.ui.addingredient.AddIngredientActivity;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FindRecipeByIngredientActivity;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(view.getContext(), AddIngredientActivity.class);

        context.startActivity(intent);
    }

//    public void onPantryClicked(View view) {
//        Intent intent = new Intent(view.getContext(), MainActivity.class);
//
//        context.startActivity(intent);
//    }
//
//    public void onRecipesClicked(View view) {
//        Intent intent = new Intent(view.getContext(), FindRecipeByIngredientActivity.class);
//
//        context.startActivity(intent);
//    }
//
//    public void onFavouritesClicked(View view) {
//        Intent intent = new Intent(view.getContext(), FavouriteRecipesActivity.class);
//
//        context.startActivity(intent);
//    }
}
