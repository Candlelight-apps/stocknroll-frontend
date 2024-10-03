package com.candlelightapps.stocknroll_frontend.ui.addingredient;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddIngredientClickHandler {
    private Ingredient ingredient;
    private Context context;
    private IngredientViewModel viewModel;

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public AddIngredientClickHandler(Ingredient ingredient, Context context, IngredientViewModel viewModel) {
        this.ingredient = ingredient;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitButtonClicked(View view) {

        if(ingredient.getName() == null || ingredient.getCategory() == null
                || ingredient.getExpiryDate() == null || ingredient.getQuantity() == 0) {

            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_LONG).show();

        } else {
            if (ingredient.getExpiryDate() != null && isDateValid(ingredient.getExpiryDate())) {

                Intent intent = new Intent(view.getContext(), MainActivity.class);

                Ingredient newIngredient = new Ingredient(ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getCategory(),
                        ingredient.getQuantity(),
                        ingredient.getExpiryDate(),
                        ingredient.getImageUrl());

                viewModel.addIngredient(newIngredient);

                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Please provide date in correct format", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isDateValid(String expiryDate) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            df.setLenient(false);
            df.parse(expiryDate);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);

        context.startActivity(intent);
    }
}
