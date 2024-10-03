package com.candlelightapps.stocknroll_frontend.ui.mainactivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.candlelightapps.stocknroll_frontend.R;
import com.candlelightapps.stocknroll_frontend.databinding.ActivityMainBinding;
import com.candlelightapps.stocknroll_frontend.model.Ingredient;
import com.candlelightapps.stocknroll_frontend.ui.viewmodel.IngredientViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IngredientViewModel ingredientViewModel;
    private List<Ingredient> ingredientList;
    private MainActivityClickHandler mainActivityClickHandler;

    private AutoCompleteTextView sortingDropdownMenu;
    private RecyclerView recyclerView;
    private InventoryAdapter inventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ingredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        mainActivityClickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(mainActivityClickHandler);

        initaliseSortingDropdownMenu();

        getAllIngredients();

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private void getAllIngredients() {
        ingredientViewModel.getIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientList = (List<Ingredient>) ingredients;

                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.inventoryRecyclerView;

        inventoryAdapter = new InventoryAdapter(this, ingredientList);

        recyclerView.setAdapter(inventoryAdapter);

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
}