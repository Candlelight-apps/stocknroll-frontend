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

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityRecipeItemViewBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    List<Ingredient> ingredientList;
    Context context;
    ArrayList<String> selectedIngredientsForSearch = new ArrayList<>();

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
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

        ImageButton addButton = holder.activityRecipeItemViewBinding.addButton;

        if (selectedIngredientsForSearch.contains(ingredient.getName())) {
            addButton.setImageResource(R.drawable.close_icon);
        } else {
            addButton.setImageResource(R.drawable.add_icon);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ingredient.getName();

                if (selectedIngredientsForSearch.contains(name)) {
                    selectedIngredientsForSearch.remove(name);
                    Toast.makeText(context, name + " removed!", Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.add_icon);
                } else {
                    selectedIngredientsForSearch.add(name);
                    Toast.makeText(context, name + " added!", Toast.LENGTH_SHORT).show();
                    addButton.setImageResource(R.drawable.close_icon);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList == null ? 0 : ingredientList.size();
    }

    public ArrayList<String> getSelectedIngredientsForSearch() {
        return selectedIngredientsForSearch;
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
