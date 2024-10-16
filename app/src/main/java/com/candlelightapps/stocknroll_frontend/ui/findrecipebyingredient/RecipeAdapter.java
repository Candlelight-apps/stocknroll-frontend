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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFoundRecipeViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipeList;
    Recipe recipe;
    Context context;
    FoundRecipesRecyclerViewInterface recyclerViewInterface;
    List<Recipe> favouriteRecipes;
    OnFavouriteBtnClickedListener onFavouriteBtnClickedListener;


    public interface OnFavouriteBtnClickedListener {
        void onFavouriteBtnClicked(Recipe recipe);
    }

    public RecipeAdapter(List<Recipe> recipeList, Context context, FoundRecipesRecyclerViewInterface recyclerViewInterface, OnFavouriteBtnClickedListener onFavouriteBtnClickedListener, List<Recipe> favouriteRecipes) {
        this.recipeList = recipeList==null? new ArrayList<>():recipeList;
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

        Recipe recipe = recipeList.get(position);
        Glide.with(context).clear(holder.activityFoundRecipeViewBinding.recipeImage);
        Glide.with(context)
                .load(recipe.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.default_recipe_image) // Add placeholder image while loading
                        .error(R.drawable.error_loading_recipe_image)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true) )
                .into(holder.activityFoundRecipeViewBinding.recipeImage);
     
        holder.activityFoundRecipeViewBinding.setRecipe(recipe);

        ImageButton favouriteButton = holder.activityFoundRecipeViewBinding.btnFavoriteRecipe;
        favouriteButton.setSelected(true);
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
//                    //Uncomment lines 96-103 to toggle between heart_filled_icon
//                    if(favouriteButton.isSelected()){
                        favouriteButton.setImageResource(R.drawable.favorite_filled_icon);
//                        favouriteButton.setSelected(false);
//                    }
//                    else{
//                        favouriteButton.setImageResource(R.drawable.favorite_outline_icon);
//                        favouriteButton.setSelected(true);
//                    }

                    if (!isCurrentRecipeFavourited) {
                        if (onFavouriteBtnClickedListener != null) {
                            recipe.setSpoonacularId((int) recipe.getId());
                            recipe.setId(0);
                            onFavouriteBtnClickedListener.onFavouriteBtnClicked(recipe);
                            Toast.makeText(context,
                                    String.format("Recipe %s added to favourites", recipe.getTitle()),
                                    Toast.LENGTH_LONG).show();

                        }

                    } else {
                        if (onFavouriteBtnClickedListener != null) {
                            onFavouriteBtnClickedListener.onFavouriteBtnClicked(recipe);
                            Toast.makeText(context,
                                    String.format("Recipe %s removed from favourites", recipe.getTitle()),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                } else {

                        if (onFavouriteBtnClickedListener != null) {
                            recipe.setSpoonacularId((int) recipe.getId());
                            recipe.setId(0);
                            onFavouriteBtnClickedListener.onFavouriteBtnClicked(recipe);
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
