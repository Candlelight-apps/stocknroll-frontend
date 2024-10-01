package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeViewBinding;

public class RecipeAdapter extends RecyclerView.Adapter<> {

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding;

        public RecipeViewHolder(ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding) {
            super(activityFoundRecipeViewBinding.getRoot());
            this.activityFoundRecipeViewBinding = activityFoundRecipeViewBinding;
        }
    }
}
