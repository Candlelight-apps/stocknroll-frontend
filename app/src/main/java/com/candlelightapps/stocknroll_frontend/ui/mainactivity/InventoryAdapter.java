package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ItemIngredientViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;
    private OnDeleteButtonClickListener onDeleteButtonClickListener;
    private IngredientViewModel viewModel;

    public interface OnDeleteButtonClickListener {
        void onButtonClick(long ingredientId);
    }

    public InventoryAdapter(Context context, List<Ingredient> ingredientList, OnDeleteButtonClickListener onDeleteButtonClickListener, IngredientViewModel viewModel) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
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

        Glide.with(context).clear(holder.itemIngredientViewBinding.ingredientImage);
        Glide.with(context)
                .load(ingredient.getImageUrl())
                .apply(new RequestOptions()
                    .placeholder(R.drawable.default_ingredient_image)
                    .error(R.drawable.error_loading_ingredient_image_square)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.itemIngredientViewBinding.ingredientImage);

        holder.itemIngredientViewBinding.setIngredient(ingredient);
        holder.itemIngredientViewBinding.executePendingBindings();

        ImageButton deleteButton = holder.itemIngredientViewBinding.btnDelete;

        deleteButton.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(String.format("Delete %s?", ingredient.getName()))
                    .setMessage("This action cannot be undone.")
                    .setPositiveButton("Yes", (dialog, v) -> {
                            if (onDeleteButtonClickListener != null) {
                                onDeleteButtonClickListener.onButtonClick(ingredient.getId());
                            }
                    })
                    .setNegativeButton("No", null)
                    .show();
            });
          
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
            return ingredientList == null ? 0 : ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        private ItemIngredientViewBinding itemIngredientViewBinding;

        public IngredientViewHolder(ItemIngredientViewBinding itemIngredientViewBinding) {
            super(itemIngredientViewBinding.getRoot());
            this.itemIngredientViewBinding = itemIngredientViewBinding;
        }
    }
}
