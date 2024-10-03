package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFindRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindRecipeByIngredientActivity extends AppCompatActivity {

    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Ingredient> ingredientFilterList;

    private SearchView ingredientSearchView;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton sortByName, sortByExpiryDate;

    private IngredientAdapter ingredientAdapter;
    private ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding;
    private FindRecipeByIngredientActivityViewModel findRecipeByIngredientActivityViewModel;
    private FindRecipeByIngredientClickHandlers findRecipeByIngredientClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityFindRecipeByIngredientBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_recipe_by_ingredient);
        findRecipeByIngredientClickHandlers = new FindRecipeByIngredientClickHandlers(this);
        activityFindRecipeByIngredientBinding.setClickHandler(findRecipeByIngredientClickHandlers);
        findRecipeByIngredientActivityViewModel = new ViewModelProvider(this).get(FindRecipeByIngredientActivityViewModel.class);

        getAllIngredients();

        ingredientSearchView = findViewById(R.id.searchItems);
        ingredientSearchView.clearFocus();

        ingredientSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterIngredientList(newText);
                return true;
            }
        });

        sortByName = findViewById(R.id.btnSortByName);
        sortByExpiryDate = findViewById(R.id.btnSortByExpiry);

        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(ingredientList, BY_NAME_ALPHABETICAL);
            }
        });

        sortByExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(ingredientList, BY_EXPIRY_DATE);
            }
        });

    }

    public Comparator<Ingredient> BY_NAME_ALPHABETICAL = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient ingredient, Ingredient i1) {
            return ingredient == null || i1 == null ? 0 : ingredient.getName().compareTo(i1.getName());
        }
    };

    public Comparator<Ingredient> BY_EXPIRY_DATE = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient ingredient, Ingredient i1) {
            return ingredient == null || i1 == null ? 0 : ingredient.getExpiryDate().compareTo(i1.getExpiryDate());
        }
    };

    private void getAllIngredients() {
        findRecipeByIngredientActivityViewModel.getAllIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredientsLiveData) {
                ingredientList = (ArrayList<Ingredient>) ingredientsLiveData;
                displayInRecyclerView();
            }
        });

    }

    public void displayInRecyclerView() {
        if (ingredientList == null || ingredientList.isEmpty()) {
                Toast.makeText(this, "Please add ingredients before searching for recipes", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
        } else {
        recyclerView = activityFindRecipeByIngredientBinding.ingredientRecyclerView;
        ingredientAdapter = new IngredientAdapter(ingredientList, this);
        recyclerView.setAdapter(ingredientAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ingredientAdapter.notifyDataSetChanged();
        }
    }

    private void filterIngredientList(String text) {

        ingredientFilterList = new ArrayList<>();

        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getCategory().toLowerCase().contains(text.toLowerCase()) ||
                    ingredient.getName().toLowerCase().contains(text.toLowerCase())) {
                ingredientFilterList.add(ingredient);
            }
        }

        if (ingredientFilterList.isEmpty()) {
            Toast.makeText(FindRecipeByIngredientActivity.this, "No Ingredients By Name or Category Found", Toast.LENGTH_SHORT).show();
        } else {
            ingredientAdapter.setIngredientFilteredList(ingredientFilterList);
        }
    }
}