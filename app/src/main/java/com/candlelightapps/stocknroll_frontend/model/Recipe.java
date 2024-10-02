package com.candlelightapps.stocknroll_frontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.candlelightapps.stocknroll_frontend.BR;

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
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
        notifyPropertyChanged(BR.sourceURL);
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
