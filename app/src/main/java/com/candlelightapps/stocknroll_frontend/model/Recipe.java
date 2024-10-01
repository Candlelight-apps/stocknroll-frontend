package com.candlelightapps.stocknroll_frontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Recipe extends BaseObservable {

     String title;
     int readyInMinutes;
     String sourceURL;
     String image;
     Boolean isFavourite;

    public Recipe(String title, int readyInMinutes, String sourceURL, String image, Boolean isFavourite) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.sourceURL = sourceURL;
        this.image = image;
        this.isFavourite = isFavourite;
    }

    public Recipe() {}

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    @Bindable
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Bindable
    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
