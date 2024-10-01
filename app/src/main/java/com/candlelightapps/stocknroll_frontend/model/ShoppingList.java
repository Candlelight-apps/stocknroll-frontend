package com.candlelightapps.stocknroll_frontend.model;

import java.util.ArrayList;

public class ShoppingList {

    ArrayList<Ingredient> shoppinglist = new ArrayList<>();

    public ShoppingList(ArrayList<Ingredient> shoppinglist) {
        this.shoppinglist = shoppinglist;
    }

    public ShoppingList() {}

    public ArrayList<Ingredient> getShoppinglist() {
        return shoppinglist;
    }

    public void setShoppinglist(ArrayList<Ingredient> shoppinglist) {
        this.shoppinglist = shoppinglist;
    }
}
