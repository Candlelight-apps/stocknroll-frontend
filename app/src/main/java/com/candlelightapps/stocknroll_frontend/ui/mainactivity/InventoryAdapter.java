package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.databinding.ItemIngredientViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;

    public InventoryAdapter(Context context, List<Ingredient> ingredientList) {
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
        if (ingredientList == null) {
            return 0;
        } else {
            return ingredientList.size();
        }
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ItemIngredientViewBinding itemIngredientViewBinding;

        public IngredientViewHolder(ItemIngredientViewBinding itemIngredientViewBinding) {
            super(itemIngredientViewBinding.getRoot());
            this.itemIngredientViewBinding = itemIngredientViewBinding;
        }
    }
}
