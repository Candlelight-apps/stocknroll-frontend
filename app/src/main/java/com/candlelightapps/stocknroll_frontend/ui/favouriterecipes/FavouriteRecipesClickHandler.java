package com.candlelightapps.stocknroll_frontend.ui.favouriterecipes;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.candlelightapps.stocknroll_frontend.ui.addingredient.AddIngredientActivity;

public class FavouriteRecipesClickHandler {

    Context context;

    public FavouriteRecipesClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(view.getContext(), FavouriteRecipesActivity.class);

        context.startActivity(intent);
    }
}
