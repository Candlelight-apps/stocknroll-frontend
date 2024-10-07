package com.candlelightapps.stocknroll_frontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.candlelightapps.stocknroll_frontend.BR;

public class Recipe extends BaseObservable {
    long id;
    String title;
    int readyInMinutes;
    String sourceUrl;
    String image;
    Boolean isFavourite;

    public Recipe(String title, int readyInMinutes, String sourceUrl, String image, Boolean isFavourite) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.sourceUrl = sourceUrl;
        this.image = image;
        this.isFavourite = isFavourite;
    }

    public Recipe() {}

    @Bindable
    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
        notifyPropertyChanged(BR.readyInMinutes);
    }

    @Bindable
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        notifyPropertyChanged(BR.sourceUrl);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
        notifyPropertyChanged(BR.favourite);
    }
}
