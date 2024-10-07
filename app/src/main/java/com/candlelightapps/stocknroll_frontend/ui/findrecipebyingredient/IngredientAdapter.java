package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

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

        CheckBox checkBox = holder.activityRecipeItemViewBinding.checkBox;
        checkBox.setChecked(ingredient.isChecked());

        checkBox.setOnCheckedChangeListener(null);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedIngredientsForSearch.add(ingredient.getName());
                    ingredient.setIsChecked(true);
                    itemStateArray.put(holder.getAdapterPosition(), true);
                }  else {
                    selectedIngredientsForSearch.remove(ingredient.getName());
                    ingredient.setIsChecked(false);
                    itemStateArray.put(holder.getAdapterPosition(), false);
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
        this.ingredientList = new ArrayList<>(ingredientFilterList);
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
