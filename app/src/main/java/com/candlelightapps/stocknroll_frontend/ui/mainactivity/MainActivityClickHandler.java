package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.candlelightapps.stocknroll_frontend.ui.addingredient.AddIngredientActivity;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(view.getContext(), AddIngredientActivity.class);

        context.startActivity(intent);
    }
}
