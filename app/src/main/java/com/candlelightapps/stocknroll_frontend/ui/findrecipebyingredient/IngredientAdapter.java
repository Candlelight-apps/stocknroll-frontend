package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityRecipeItemViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    List<Ingredient> ingredientList;
    Context context;

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ActivityRecipeItemViewBinding activityRecipeItemViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.activity_recipe_item_view,
                parent,
                false
        );

        return new IngredientViewHolder(activityRecipeItemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.activityRecipeItemViewBinding.setIngredient(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void setIngredientFilteredList(ArrayList<Ingredient> ingredientFilterList) {
        this.ingredientList = ingredientFilterList;
        notifyDataSetChanged();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ActivityRecipeItemViewBinding activityRecipeItemViewBinding;

        public IngredientViewHolder(ActivityRecipeItemViewBinding activityRecipeItemViewBinding) {
            super(activityRecipeItemViewBinding.getRoot());
            this.activityRecipeItemViewBinding = activityRecipeItemViewBinding;
        }
    }
}
