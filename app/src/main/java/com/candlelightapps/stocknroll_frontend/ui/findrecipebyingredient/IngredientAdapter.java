package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFindRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    List<Ingredient> ingredientList;
    Context context;

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.activity_find_recipe_by_ingredient,
                parent,
                false
        );

        return new IngredientViewHolder(activityFindRecipeByIngredientBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding;

        public IngredientViewHolder(ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding) {
            super(activityFindRecipeByIngredientBinding.getRoot());
            this.activityFindRecipeByIngredientBinding = activityFindRecipeByIngredientBinding;
        }
    }
}
