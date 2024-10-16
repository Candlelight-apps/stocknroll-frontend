package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityMainBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.favouriterecipes.FavouriteRecipesActivity;
import com.candlelightapps.stocknroll_frontend.ui.findrecipebyingredient.FindRecipeByIngredientActivity;
import com.candlelightapps.stocknroll_frontend.ui.shoppinglist.ShoppingListActivity;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements InventoryAdapter.OnDeleteButtonClickListener {

    private ActivityMainBinding binding;
    private IngredientViewModel ingredientViewModel;
    private List<Ingredient> ingredientList;
    private List<Ingredient> inventoryListFiltered;
    private List<Ingredient> allIngredientList;

    private MainActivityClickHandler mainActivityClickHandler;
    private AutoCompleteTextView sortingDropdownMenu;
    private RecyclerView recyclerView;
    private InventoryAdapter inventoryAdapter;
    private String sortByName = "Name";
    private String sortByExpiry = "Expiry Date";
    private String sortByStock = "Stock Level";

    private BottomNavigationView bottomNavigationView;

    // **********************
    //      onCreate
    // **********************

    private String viewAll = "View All";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ingredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        mainActivityClickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(mainActivityClickHandler);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        initaliseSortingDropdownMenu();
        getAllIngredientsBy(sortByName, ingredientList);

        AutoCompleteTextView ddSortingView = findViewById(R.id.dropdown_sorting_inventory);
        ddSortingView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedSortOption = adapterView.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, "Sorting by " + selectedSortOption, Toast.LENGTH_SHORT).show();
 
                if(selectedSortOption.equalsIgnoreCase(sortByStock)) {
                    getAllIngredientsBy(sortByStock, ingredientList);
                }
                else if(selectedSortOption.equalsIgnoreCase(sortByExpiry)) {
                    getAllIngredientsBy(sortByExpiry, ingredientList);
                }
                else if(selectedSortOption.equalsIgnoreCase(sortByName)) {
                    getAllIngredientsBy(sortByName, ingredientList);
                }
            }
        });

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String selectedTab = tab.getText().toString();
                Toast.makeText(MainActivity.this, "Selected Tab: " + selectedTab, Toast.LENGTH_SHORT).show();
                getAllIngredientsIn(selectedTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
          
        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                if (item.getItemId() == R.id.pantry) {
                    return true;
                } else if (item.getItemId() == R.id.recipes) {
                    intent = new Intent(MainActivity.this, FindRecipeByIngredientActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.favourites) {
                    intent = new Intent(MainActivity.this, FavouriteRecipesActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.shopping_list) {
                    intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    // ***************************
    //      onCreateOptionsMenu
    // ***************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Find in your pantry...");
        searchView.setBackgroundResource(R.drawable.search_view_background);
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getColor(R.color.blue));
        ImageView closeButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.close_icon);
        closeButton.setOnClickListener(view -> {
            // Clear the search query and collapse the SearchView
            searchView.setQuery("", false);
            searchView.clearFocus();
            searchItem.collapseActionView();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterIngredientList(newText);
                recyclerView.scrollToPosition(0);
                inventoryAdapter.notifyDataSetChanged();
                return true;
            }
        });

        return true;
    }

    private void getAllIngredientsIn(String selectedProductTab) {

        if(selectedProductTab.equalsIgnoreCase(viewAll)) {
            getAllIngredientsBy(sortByName, null);
        } else {
            ingredientList = allIngredientList.stream()
                    .filter(ingredient -> ingredient.getCategory().equalsIgnoreCase(selectedProductTab))
                    .collect(Collectors.toList());

            inventoryAdapter = new InventoryAdapter(this, ingredientList, this, ingredientViewModel);
            recyclerView.setAdapter(inventoryAdapter);
            displayInRecyclerView();
        }
    }

    private void getAllIngredientsBy(String sortOption, List<Ingredient> filteredIngredientList) {
      
    ingredientViewModel.getIngredients().observe(this, new Observer<List<Ingredient>>() {
      
        @Override
        public void onChanged(List<Ingredient> ingredientsFromLiveData) {
            MainActivity.this.ingredientList = (List<Ingredient>) ingredientsFromLiveData;
            allIngredientList = MainActivity.this.ingredientList;
          
            if(filteredIngredientList != null) {
                ingredientList = filteredIngredientList;
            }

            if(sortOption.equalsIgnoreCase(sortByStock)) {
                Collections.sort(MainActivity.this.ingredientList, new Comparator<Ingredient>() {
                  
                    @Override
                    public int compare(Ingredient i1, Ingredient i2) {
                        return i1 == null || i2 == null || i1.getName() == null || i2.getName() == null ? 0 : Integer.compare(i2.getQuantity(), i1.getQuantity());
                    }
                });
              
            } else if(sortOption.equalsIgnoreCase(sortByExpiry)) {
                Collections.sort(MainActivity.this.ingredientList, new Comparator<Ingredient>() {
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
                });
              
            } else {
                Collections.sort(MainActivity.this.ingredientList, new Comparator<Ingredient>() {
                  
                    @Override
                    public int compare(Ingredient o1, Ingredient o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
            }
            setUpdatedInventoryList();
        }
    });
}
  
    @Override
    public void onButtonClick(long ingredientId) {
        ingredientViewModel.getIsDeleted().observe(this, isDeleted -> {
            if (isDeleted) {
                getAllIngredientsBy(sortByName, ingredientList);
            }
        });
        ingredientViewModel.deleteIngredient(ingredientId);
    }

    private void setUpdatedInventoryList() {
        recyclerView = binding.inventoryRecyclerView;
        inventoryAdapter = new InventoryAdapter(this, ingredientList, this, ingredientViewModel);
        recyclerView.setAdapter(inventoryAdapter);

        displayInRecyclerView();
    }

    private void displayInRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        inventoryAdapter.notifyDataSetChanged();
    }

    private void initaliseSortingDropdownMenu() {
        sortingDropdownMenu = binding.dropdownSortingInventory;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sorting_options,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortingDropdownMenu.setAdapter(adapter);
    }

    private void filterIngredientList(String text) {

        inventoryListFiltered = new ArrayList<>();

        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getName().toLowerCase().contains(text.toLowerCase())) {
                inventoryListFiltered.add(ingredient);
            }
        }

        if (inventoryListFiltered.isEmpty()) {
            Toast.makeText(MainActivity.this, "No items found", Toast.LENGTH_SHORT).show();

        }
        inventoryAdapter.setInventoryFilteredList(inventoryListFiltered);

    }
}

