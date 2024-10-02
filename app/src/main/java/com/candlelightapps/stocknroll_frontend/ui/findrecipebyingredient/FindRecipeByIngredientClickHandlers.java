package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;

public class FindRecipeByIngredientClickHandlers {

    Context context;

    public void onBackBtnClicked(View view) {
        new AlertDialog.Builder(context)
                .setTitle("Back to Home")
                .setMessage("Are you sure you want to go Home? All progress will be lost.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
