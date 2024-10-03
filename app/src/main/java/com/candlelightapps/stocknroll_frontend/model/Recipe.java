package com.candlelightapps.stocknroll_frontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.candlelightapps.stocknroll_frontend.BR;

public class Recipe extends BaseObservable implements Parcelable {

     String title;
     int readyInMinutes;
     String sourceURL;
     String image;
     Boolean favourite;

    public Recipe() {}

    public Recipe(String title, int readyInMinutes, String sourceURL, String image, Boolean favourite) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.sourceURL = sourceURL;
        this.image = image;
        this.favourite = favourite;
    }

    protected Recipe(Parcel in) {
        title = in.readString();
        readyInMinutes = in.readInt();
        sourceURL = in.readString();
        image = in.readString();
        byte tmpFavourite = in.readByte();
        favourite = tmpFavourite == 0 ? null : tmpFavourite == 1;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
        notifyPropertyChanged(BR.favourite);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(readyInMinutes);
        parcel.writeString(sourceURL);
        parcel.writeString(image);
        parcel.writeByte((byte) (favourite == null ? 0 : favourite ? 1 : 2));
    }
}
