package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFindRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    List<Ingredient> ingredientList;
    Context context;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding;

        public IngredientViewHolder(ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding) {
            super(activityFindRecipeByIngredientBinding.getRoot());
            this.activityFindRecipeByIngredientBinding = activityFindRecipeByIngredientBinding;
        }
    }
}
