package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;

public class IngredientAdapter extends RecyclerView.Adapter<>{

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView quantity;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.nameOfItem);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
        }
    }
}
