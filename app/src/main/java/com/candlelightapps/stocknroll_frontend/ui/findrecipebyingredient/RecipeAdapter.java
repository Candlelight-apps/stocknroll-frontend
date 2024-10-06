package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipeList;
    Recipe recipe;
    Recipe recipeToAddToFavorites;
    Recipe recipeToRemoveFromFavourites;
    Context context;
    FoundRecipesRecyclerViewInterface recyclerViewInterface;
    List<Recipe> favouriteRecipes;

    public RecipeAdapter(List<Recipe> recipeList, Context context, FoundRecipesRecyclerViewInterface recyclerViewInterface) {
        this.recipeList = recipeList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
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

        return new RecipeViewHolder(activityFoundRecipeViewBinding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        recipe = recipeList.get(position);

        Glide.with(holder.itemView.getContext()).load(recipeList.get(position).getImage()).into(holder.activityFoundRecipeViewBinding.recipeImage);

        holder.activityFoundRecipeViewBinding.setRecipe(recipe);

        ImageButton favouriteButton = holder.activityFoundRecipeViewBinding.btnFavoriteRecipe;
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isCurrentRecipeFavourited = false;
                for (int i = 0; i < favouriteRecipes.size(); i ++) {
                    if (recipe == favouriteRecipes.get(i)) {
                        isCurrentRecipeFavourited = true;
                    }
                }
                if (!isCurrentRecipeFavourited) {
                    recipeToAddToFavorites = recipe;
                } else {
                    recipeToRemoveFromFavourites = recipe;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public Recipe getRecipeToAddToFavorites() {
        return recipeToAddToFavorites;
    }

    public Recipe getRecipeToRemoveFromFavourites() {
        return recipeToRemoveFromFavourites;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding;

        public RecipeViewHolder(ActivityFoundRecipeViewBinding activityFoundRecipeViewBinding, FoundRecipesRecyclerViewInterface recyclerViewInterface) {
            super(activityFoundRecipeViewBinding.getRoot());
            this.activityFoundRecipeViewBinding = activityFoundRecipeViewBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
