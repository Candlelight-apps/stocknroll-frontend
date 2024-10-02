package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;

public class FindRecipeByIngredientClickHandlers {

    Context context;

    public void onBackBtnClicked(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        context.startActivity(intent);
    }
}
