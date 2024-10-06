package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.RecipeViewModel;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipeList;
    Recipe recipe;
    List<Recipe> recipesToAddToFavorites;
    List<Recipe> recipesToRemoveFromFavourites;
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
                    recipesToAddToFavorites.add(recipe);
                    Toast.makeText(context,
                            String.format("Recipe %s selected", recipe.getTitle()),
                            Toast.LENGTH_SHORT).show();

                } else {
                    recipesToRemoveFromFavourites.remove(recipe);
                    Toast.makeText(context,
                            String.format("Recipe %s de-selected", recipe.getTitle()),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public List<Recipe> getRecipesToAddToFavorites() {
        return recipesToAddToFavorites;
    }

    public List<Recipe> getRecipesToRemoveFromFavourites() {
        return recipesToRemoveFromFavourites;
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
