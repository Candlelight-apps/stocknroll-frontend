package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.util.Log;
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

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipeList;
    Recipe recipe;
    Context context;
    FoundRecipesRecyclerViewInterface recyclerViewInterface;
    List<Recipe> favouriteRecipes;
    OnFavouriteBtnClickedListener onFavouriteBtnClickedListener;

    public interface OnFavouriteBtnClickedListener {
        void onFavouriteBtnClicked(Recipe recipe, boolean isFavourite);
    }

    public RecipeAdapter(List<Recipe> recipeList, Context context, FoundRecipesRecyclerViewInterface recyclerViewInterface, OnFavouriteBtnClickedListener onFavouriteBtnClickedListener, List<Recipe> favouriteRecipes) {
        this.recipeList = recipeList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.onFavouriteBtnClickedListener = onFavouriteBtnClickedListener;
        this.favouriteRecipes = favouriteRecipes;
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
        Log.i("Position int", "**********Recipe click at position ::" + position);
        Log.i("Favourite recipes", "**********Favourite Recipes:" + favouriteRecipes);

        recipe = recipeList.get(position);

        Glide.with(holder.itemView.getContext()).load(recipeList.get(position).getImage()).into(holder.activityFoundRecipeViewBinding.recipeImage);

        holder.activityFoundRecipeViewBinding.setRecipe(recipe);

        ImageButton favouriteButton = holder.activityFoundRecipeViewBinding.btnFavoriteRecipe;

        favouriteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.i("Recipe select", "*******Recipe clicked" + recipe.getTitle());
                boolean isCurrentRecipeFavourited = false;
                if (!(favouriteRecipes == null)) {
                    Log.i("Favourite recipes empty", "*********Favourite recipes are empty*******");
                    for (int i = 0; i < favouriteRecipes.size(); i++) {
                        if (recipe.getTitle().equals(favouriteRecipes.get(i).getTitle())) {
                            isCurrentRecipeFavourited = true;
                        }
                    }
                    if (!isCurrentRecipeFavourited) {
                        if (onFavouriteBtnClickedListener != null) {
                            recipe.setSpoonacularId((int) recipe.getId());
                            recipe.setId(0);
                            onFavouriteBtnClickedListener.onFavouriteBtnClicked(recipe, true);
                            Toast.makeText(context,
                                    String.format("Recipe %s added to favourites", recipe.getTitle()),
                                    Toast.LENGTH_LONG).show();

                        }

                    } else {
                        if (onFavouriteBtnClickedListener != null) {
                            Toast.makeText(context,
                                    String.format("Recipe %s already in favourites", recipe.getTitle()),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                } else {

                        if (onFavouriteBtnClickedListener != null) {
                            recipe.setSpoonacularId((int) recipe.getId());
                            recipe.setId(0);
                            onFavouriteBtnClickedListener.onFavouriteBtnClicked(recipe, true);
                            Toast.makeText(context,
                                    String.format("Recipe %s added to favourites", recipe.getTitle()),
                                    Toast.LENGTH_LONG).show();

                        }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
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
