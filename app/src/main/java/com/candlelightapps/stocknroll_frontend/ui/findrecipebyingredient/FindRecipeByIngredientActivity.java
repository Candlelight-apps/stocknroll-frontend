package com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityFindRecipeByIngredientBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.mainactivity.MainActivity;
import com.candlelightapps.stocknroll_frontend.ui.shoppinglist.ShoppingListActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindRecipeByIngredientActivity extends AppCompatActivity {

    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Ingredient> ingredientFilterList;
    private ArrayList<String> ingredientsForRecipeSearch;
    private SearchView ingredientSearchView;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton sortByName, sortByExpiryDate;
    private BottomNavigationView bottomNavigationView;
    private SwitchCompat switchFindRecipe;
    private LinearLayout layoutEditFilters;
    private Button submitButton;
    private IngredientAdapter ingredientAdapter;
    private ActivityFindRecipeByIngredientBinding activityFindRecipeByIngredientBinding;
    private FindRecipeByIngredientClickHandlers findRecipeByIngredientClickHandlers;
    private IngredientViewModel ingredientViewModel;
    private AutoCompleteTextView dietTypeDropdown;
    private AutoCompleteTextView cuisineDropdown;
    private AutoCompleteTextView intolerancesDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ingredientsForRecipeSearch = new ArrayList<>();

        activityFindRecipeByIngredientBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_recipe_by_ingredient);

        findRecipeByIngredientClickHandlers = new FindRecipeByIngredientClickHandlers(this, ingredientsForRecipeSearch);

        activityFindRecipeByIngredientBinding.setClickHandler(findRecipeByIngredientClickHandlers);
        ingredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        switchFindRecipe = activityFindRecipeByIngredientBinding.switchFindRecipe;
        layoutEditFilters = activityFindRecipeByIngredientBinding.layoutEditFilters;
        bottomNavigationView = activityFindRecipeByIngredientBinding.bottomNavigation;
        recyclerView = activityFindRecipeByIngredientBinding.ingredientRecyclerView;

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

        handleSwitchToggle();
        cuisineDropdown = findViewById(R.id.cuisine_dropdown);
        cuisineDropdownSetup();
        dietTypeDropdown = findViewById(R.id.diet_type_dropdown);
        dietDropdownSetup();
        intolerancesDropdown = findViewById(R.id.intolerances_dropdown);
        intoleranceDropdownSetup();

        submitButton = findViewById(R.id.button_search);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!switchFindRecipe.isChecked()) {
                    ingredientsForRecipeSearch = ingredientAdapter.getSelectedIngredientsForSearch();
                    Intent intent = new Intent(view.getContext(), FoundRecipeByIngredient.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("ingredient_list", ingredientsForRecipeSearch);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    String diet = dietTypeDropdown.getText().toString();
                    String cuisine = cuisineDropdown.getText().toString();
                    String intolerances = intolerancesDropdown.getText().toString();
                    ArrayList<String> criteria = new ArrayList<>();
                    criteria.add(cuisine);
                    criteria.add(diet);
                    criteria.add(intolerances);
                    Intent intent = new Intent(view.getContext(), FoundRecipeByIngredient.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("criteria", criteria);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        bottomNavigationView = activityFindRecipeByIngredientBinding.bottomNavigation;
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                if (item.getItemId() == R.id.pantry) {
                    intent = new Intent(FindRecipeByIngredientActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.recipes) {
                    // Already in Recipes activity, no need to restart
                    return true;
                } else if (item.getItemId() == R.id.favourites) {
                    intent = new Intent(FindRecipeByIngredientActivity.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.shopping_list) {
                    intent = new Intent(FindRecipeByIngredientActivity.this, ShoppingListActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

    }


//        sortByName = findViewById(R.id.btnSortByName);
//        sortByExpiryDate = findViewById(R.id.btnSortByExpiry);

//        sortByName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ingredientList.sort(BY_NAME_ALPHABETICAL);
//                ingredientAdapter.notifyDataSetChanged();
//            }
//        });
//
//        sortByExpiryDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ingredientList.sort(BY_EXPIRY_DATE);
//                ingredientAdapter.notifyDataSetChanged();
//            }
//        });

    public Comparator<Ingredient> BY_NAME_ALPHABETICAL = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient ingredient, Ingredient i1) {
            return ingredient == null || i1 == null || ingredient.getName() == null || i1.getName() == null ? 0 : ingredient.getName().compareTo(i1.getName());
        }
    };

    public Comparator<Ingredient> BY_EXPIRY_DATE = new Comparator<Ingredient>() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public int compare(Ingredient ingredient, Ingredient i1) {
            if (ingredient == null || i1 == null || ingredient.getExpiryDate() == null || i1.getExpiryDate() == null) {
                return 0;
            } else {
                LocalDate ingredientDate = LocalDate.parse(ingredient.getExpiryDate(), dateTimeFormatter);
                LocalDate i1Date = LocalDate.parse(i1.getExpiryDate(), dateTimeFormatter);

                return ingredientDate.compareTo(i1Date);
            }
        }
    };

    private void getAllIngredients() {
        ingredientViewModel.getIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredientsLiveData) {
                ingredientList = (ArrayList<Ingredient>) ingredientsLiveData;
                displayInRecyclerView();
            }
        });

    }

    private void handleSwitchToggle() {
        switchFindRecipe.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                recyclerView.setVisibility(View.GONE);
                layoutEditFilters.setVisibility(View.VISIBLE);
                ingredientSearchView.setVisibility(View.GONE);
                switchFindRecipe.setText("By criteria   ");

            } else {
                recyclerView.setVisibility(View.VISIBLE);
                layoutEditFilters.setVisibility(View.GONE);
                ingredientSearchView.setVisibility(View.VISIBLE);
                switchFindRecipe.setText("By ingredient   ");
            }
        });
    }

    public void displayInRecyclerView() {
        if (ingredientList == null || ingredientList.isEmpty()) {
            Toast.makeText(this, "Please add ingredients before searching for recipes", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        } else {
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
        }
        ingredientAdapter.setIngredientFilteredList(ingredientFilterList);
    }

    private void intoleranceDropdownSetup() {

        String[] intolerances = new String[]{
                "None","Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame", "Shellfish", "Soy",
                "Sulfite", "Tree Nut", "Wheat"
        };
        ArrayAdapter<String> intoleranceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, intolerances);
        intolerancesDropdown.setAdapter(intoleranceAdapter);
    }

    private void dietDropdownSetup() {

        String[] diets = new String[]{
                "Any","Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian", "Vegan",
                "Pescetarian", "Paleo", "Primal", "Low FODMAP", "Whole30"
        };
        ArrayAdapter<String> dietAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, diets);
        dietTypeDropdown.setAdapter(dietAdapter);
    }

    private void cuisineDropdownSetup() {

        String[] cuisines = new String[]{
                "Any","African", "Asian", "American", "British", "Cajun", "Caribbean", "Chinese", "Eastern European",
                "European", "French", "German", "Greek", "Indian", "Irish", "Italian", "Japanese", "Jewish",
                "Korean", "Latin American", "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "Southern",
                "Spanish", "Thai", "Vietnamese"
        };
        ArrayAdapter<String> cuisineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cuisines);
        cuisineDropdown.setAdapter(cuisineAdapter);
    }

}

