package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    Context context;
    FoundRecipesRecyclerViewInterface recyclerViewInterface;

    public RecipeAdapter(List<Recipe> recipeList, Context context, FoundRecipesRecyclerViewInterface recyclerViewInterface) {
        this.recipeList = recipeList==null? new ArrayList<>():recipeList;
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
