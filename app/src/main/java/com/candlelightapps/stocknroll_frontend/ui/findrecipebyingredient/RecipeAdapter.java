package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipeList;
    Context context;

    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.activity_found_recipe_view,
                parent,
                false
        );

        return new RecipeViewHolder(activityFoundRecipeViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.activityFoundRecipeViewBinding.setRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding;

        public RecipeViewHolder(ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding) {
            super(activityFoundRecipeViewBinding.getRoot());
            this.activityFoundRecipeViewBinding = activityFoundRecipeViewBinding;
        }
    }
}
