package com.candlelightapps.stocknroll_frontend.ui.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityShoppingListBinding;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FindRecipeByIngredientActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingListActivity extends AppCompatActivity {

    private ActivityShoppingListBinding binding;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list);

        bottomNavigationView = binding.bottomNavigation;
        bottomNavigationView.setSelectedItemId(R.id.shopping_list);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                if (item.getItemId() == R.id.pantry) {
                    intent = new Intent(ShoppingListActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.recipes) {
                    intent = new Intent(ShoppingListActivity.this, FindRecipeByIngredientActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else // Already in Shopping List activity, no need to restart
                    if (item.getItemId() == R.id.favourites) {
                    intent = new Intent(ShoppingListActivity.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else return item.getItemId() == R.id.shopping_list;
            }
        });

    }
}