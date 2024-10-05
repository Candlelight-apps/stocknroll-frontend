package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.databinding.ItemIngredientViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;
    private IngredientViewModel viewModel;

    public InventoryAdapter(Context context, List<Ingredient> ingredientList,IngredientViewModel viewModel) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.viewModel = viewModel;
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

        holder.itemIngredientViewBinding.btnDecreaseQuantity.setOnClickListener(v -> {
            int presentQuantity = ingredient.getQuantity();
            int updatedQuantity;
            if (presentQuantity > 0) {
                updatedQuantity = presentQuantity - 1;
                ingredient.setQuantity(updatedQuantity);
                viewModel.updateIngredient(ingredient.getId(),updatedQuantity);
                if (updatedQuantity == 0) {
                    new AlertDialog.Builder(context)
                            .setTitle("Shopping Time!")
                            .setMessage("Do you want to add " + ingredient.getName() + " to your shopping list?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, ingredient.getName() + " added to shopping list.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }
            notifyItemChanged(position);

        });

        holder.itemIngredientViewBinding.btnIncreaseQuantity.setOnClickListener(v -> {
            int updatedQuantity = ingredient.getQuantity()+1;
            ingredient.setQuantity(updatedQuantity);
            viewModel.updateIngredient(ingredient.getId(),updatedQuantity);
            notifyItemChanged(position);
        });
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
