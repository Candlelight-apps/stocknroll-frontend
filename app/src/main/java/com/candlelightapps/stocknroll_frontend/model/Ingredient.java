package com.candlelightapps.stocknroll_frontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.candlelightapps.stocknroll_frontend.BR;

import java.time.LocalDate;

public class Ingredient extends BaseObservable {

    String name;
    String category;
    int quantity;
    LocalDate expiryDate;
    String imageUrl; //To be implemented later

    public Ingredient(String name, String category, int quantity, LocalDate expiryDate, String imageUrl) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.imageUrl = imageUrl;
    }

    public Ingredient() {}

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    @Bindable
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        notifyPropertyChanged(BR.expiryDate);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
