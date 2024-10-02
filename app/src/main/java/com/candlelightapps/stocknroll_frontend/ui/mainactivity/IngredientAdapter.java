package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.content.Context;
import android.media.RouteListingPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ItemIngredientViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIngredientViewBinding itemIngredientViewBinding = ItemIngredientViewBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new IngredientViewHolder(itemIngredientViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.itemIngredientViewBinding.setIngredient(ingredient);
        holder.itemIngredientViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ItemIngredientViewBinding itemIngredientViewBinding;

        public IngredientViewHolder(ItemIngredientViewBinding itemIngredientViewBinding) {
            super(itemIngredientViewBinding.getRoot());
            this.itemIngredientViewBinding = itemIngredientViewBinding;
        }
    }
}
