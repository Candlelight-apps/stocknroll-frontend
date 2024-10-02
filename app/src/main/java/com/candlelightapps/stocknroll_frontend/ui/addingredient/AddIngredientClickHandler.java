package com.candlelightapps.stocknroll_frontend.ui.addingredient;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

public class AddIngredientClickHandler {
    private Ingredient ingredient;
    private Context context;
    private IngredientViewModel viewModel;

    public AddIngredientClickHandler(Ingredient ingredient, Context context, IngredientViewModel viewModel) {
        this.ingredient = ingredient;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitButtonClicked(View view) {

        if(ingredient.getName().isBlank() || ingredient.getCategory().isBlank()
                || (ingredient.getExpiryDate().isBlank()) || (ingredient.getQuantity() == 0)) {

            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_LONG).show();

        } else {
            Intent intent = new Intent(view.getContext(), MainActivity.class);

            Ingredient newIngredient = new Ingredient(ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getCategory(),
                    ingredient.getQuantity(),
                    ingredient.getExpiryDate(),
                    ingredient.getImageUrl());

            viewModel.addAlbum(newIngredient);

            context.startActivity(intent);
        }
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);

        context.startActivity(intent);
    }
}
